# JVM Report (concise)

This project targets the Java Virtual Machine (JVM). The following gives a brief overview of the JVM components and runtime behavior relevant to this application.

Class loader subsystem
- Bootstrap class loader: loads core Java classes (rt/jdk modules). 
- Platform and application class loaders: load JDK extension and application classes (the compiled classes in `target/classes`).

Runtime data areas
- Method Area / Metaspace: class metadata and static information.
- Heap: all object instances (Patients, Doctors, Appointments, utility singletons) are allocated here.
- Java Stacks: per-thread stack frames for local variables and method calls.
- Native method stacks and registers handled by JVM implementation.

Execution engine and JIT
- Bytecode interpretation is handled by the execution engine; HotSpot's JIT compiler optimizes hot methods at runtime to native code.
- For long-running processes, JIT can significantly improve throughput for frequently executed code paths (e.g., CSV parsing loops).

Garbage collection
- Modern JVMs (HotSpot) provide collectors like G1 and other experimental collectors (ZGC, Shenandoah). Default collector may vary by JDK and flags.
- This small application does not require GC tuning; large datasets may benefit from heap sizing via `-Xms`/`-Xmx`.

Portability and JVM flags
- Use `java -version` to verify JDK 23.
- Recommended runtime flags when required: `-Xms256m -Xmx1g` (adjust as needed).

Security and modules
- This project uses the classpath (not Java modules). If migrating to the module system (`module-info.java`), be mindful of exported packages and reflective access.
# JVM Report (placeholder)

This document will be filled with the JVM report: Class Loader, Runtime Data Areas, Execution Engine, JIT vs Interpreter, and Write Once Run Anywhere.
