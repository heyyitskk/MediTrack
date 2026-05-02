# JVM Report

This project targets the Java Virtual Machine (JVM). The following gives a brief overview of the JVM components and runtime behavior relevant to this application.

## Class Loader
- Bootstrap (primordial) class loader: loads core JDK classes and runtime (lowest level).
- Platform (or extension) class loader: loads platform libraries and extension modules.
- Application (system) class loader: loads application classes (compiled `target/classes` and dependencies on the classpath).
- Delegation model: loaders delegate to parent first, ensuring core classes are resolved by the bootstrap loader. Useful for class isolation and avoiding duplicate core definitions.

## Runtime Data Areas
- Method Area / Metaspace: class metadata and static information.
- Heap: all object instances (Patients, Doctors, Appointments, utility singletons) are allocated here.
- Java Stacks: per-thread stack frames for local variables and method calls.
- Native method stacks and registers handled by JVM implementation.

## Execution Engine
- The execution engine reads and executes JVM bytecode (from class files) by interpreting instructions and managing frames/operand stacks.
- It handles bytecode verification, linking (resolution of symbolic references), and dispatch to native methods via JNI when necessary.
- Modern JVMs combine an interpreter with a JIT compiler to balance startup latency and runtime performance.

## JIT Compiler vs Interpreter
- Interpreter: executes bytecode directly; low startup cost and simple execution but lower runtime performance.
- JIT Compiler: identifies "hot" methods at runtime and compiles them to optimized native code (tiered compilation, inlining, escape analysis, etc.), improving steady-state throughput.
- Trade-offs: JIT introduces warm-up time and compilation overhead but yields significant performance for long-running or frequently executed code paths (e.g., CSV parsing loops or heavy business logic).

## Write Once, Run Anywhere
- Java compiles source to platform-independent bytecode which runs on any compatible JVM implementation: compile once, run on multiple OS/architectures with a JVM.
- Portability caveats: native libraries, platform-specific file paths/line endings, locale/timezone differences, and use of JVM-specific flags or modules can affect portability; test on target platforms when needed.


