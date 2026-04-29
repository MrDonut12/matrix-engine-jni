# matrix-engine-jni

This repository is a small, Windows-focused reference project that demonstrates **JNI (Java Native Interface)**: calling **C++** code from **Java**.

Primary goals:

- Understand the end-to-end JNI workflow (Java `native` methods → C/C++ implementation → build a `.dll` → load it at runtime).
- Practice **data bridging** between Java and C++ (e.g., Java `int[][]` ⇄ C++ `std::vector<std::vector<jint>>`).
- Learn to diagnose common JNI issues (DLL discovery, linker errors, and signature/type mismatches).

This project is intended for **learning and experimentation** rather than production-ready architecture or performance tuning.

## High-level structure

- Java:
  - `src/main/java/com/mrdonut/matrix/nativebridge/NativeMatrix.java`: declares `native` methods and loads the DLL via `System.loadLibrary("matrix")`.
  - `src/main/java/com/mrdonut/matrix/service/MatrixService.java`: a thin wrapper that converts `Matrix` ⇄ `int[][]` and delegates to `NativeMatrix`.
  - `src/main/java/com/mrdonut/matrix/model/Matrix.java`: a simple matrix model backed by `int[][]`.
  - `src/main/java/com/mrdonut/matrix/Main.java`: console demo program.
- C++:
  - `src/main/cpp/src/jni_bridge.cpp`: JNI entry points (functions named `Java_<package>_<Class>_<method>`).
  - `src/main/cpp/src/matrix.cpp`: the C++ matrix operations.
  - `src/main/cpp/include/matrix.h`: C++ declarations used by the implementation.

Across the JNI boundary, this project uses Java `int[][]` and converts it to/from a C++ `MatrixData` type (`std::vector<std::vector<jint>>`).

## Prerequisites (Windows)

- A **JDK** (for `jni.h`, `javac`, and `java`).
- **MSYS2 MinGW-w64** (for `g++` and the linker to produce a Windows DLL).
  - Example toolchain path: `D:\msys2\mingw64\bin\g++.exe`

Notes:

- You can compile the native code against JDK 8/11/17/etc. as long as you point include paths to the correct `include` folders.
- The Java runtime used to execute the demo can be different from the JDK used to compile, as long as it is the same architecture (typically x64) and can locate the DLL at runtime.

## Build the native library (`matrix.dll`)

From the repository root:

1) Create an output directory:

```powershell
cd D:\GITHUB\matrix-engine-jni
mkdir out -Force | Out-Null
```

2) Build the DLL with `g++` (adjust the JDK path for your machine):

```powershell
g++ -shared -O2 -std=c++17 `
  -I"C:\Program Files\Java\jdk1.8.0_231\include" `
  -I"C:\Program Files\Java\jdk1.8.0_231\include\win32" `
  -o out\matrix.dll `
  src\main\cpp\src\jni_bridge.cpp `
  src\main\cpp\src\matrix.cpp
```

Output: `out\matrix.dll`

## Build the Java classes (`.class`)

Compile all Java sources into `out\classes`:

```powershell
cd D:\GITHUB\matrix-engine-jni
mkdir out\classes -Force | Out-Null
$files = Get-ChildItem src\main\java -Recurse -Filter *.java | ForEach-Object { $_.FullName }
javac -sourcepath src\main\java -d out\classes $files
```

## Run the demo program

`NativeMatrix` uses:

```java
System.loadLibrary("matrix");
```

So the JVM must be able to find `matrix.dll` at runtime.

Run `Main` like this:

```powershell
cd D:\GITHUB\matrix-engine-jni
java "-Djava.library.path=out" -cp out\classes com.mrdonut.matrix.Main
```

If you do not want to `cd` into the repo every time, use absolute paths:

```powershell
java "-Djava.library.path=D:\GITHUB\matrix-engine-jni\out" -cp D:\GITHUB\matrix-engine-jni\out\classes com.mrdonut.matrix.Main
```

## (Optional) Generate JNI headers

If you want to regenerate JNI headers from Java sources, you can use `javac -h`:

```powershell
cd D:\GITHUB\matrix-engine-jni
mkdir src\main\cpp\src -Force | Out-Null
javac -h src\main\cpp\src -sourcepath src\main\java -d out\classes src\main\java\com\mrdonut\matrix\nativebridge\NativeMatrix.java
```

Important: JNI function names are derived from package/class/method signatures. Changing Java signatures requires corresponding updates on the C++ side.

## Common errors (and what they mean)

- `cannot open output file out\matrix.dll: No such file or directory`
  - `out\` does not exist. Create it before linking.
- `java.lang.UnsatisfiedLinkError: no matrix in java.library.path`
  - The JVM cannot find `matrix.dll`. Use `-Djava.library.path=...` or place the DLL somewhere on `PATH`.
- `undefined reference to ...` while building the DLL
  - A linker error. Most commonly:
    - a function is declared but not defined,
    - the signature differs between declaration and definition (even `const`, references `&`, or namespaces matter in C++),
    - or a required `.cpp` file is not included in the link command.

## Suggested learning exercises

- Trace the data conversion path:
  - Java `int[][]` → JNI → C++ `MatrixData`
  - C++ `MatrixData` → JNI → Java `int[][]`
- Add a new native operation and implement it end-to-end:
  1) declare a new `native` method in Java
  2) generate or inspect the JNI header/signature
  3) implement the JNI function in C++
  4) rebuild the DLL and run the demo again
