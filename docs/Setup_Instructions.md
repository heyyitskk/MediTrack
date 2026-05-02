# Setup & Run Instructions

Prerequisites
- Java Development Kit (JDK) 23 installed and `JAVA_HOME` set.
- Maven 3.6+ (or newer) installed and on `PATH`.

Build
1. From project root, compile sources:

```bash
mvn compile
```

2. Optionally build packaged classes:

```bash
mvn -DskipTests package
```

Run (development)
1. Run the console application (uses compiled classes):

On Windows:

```powershell
# after mvn compile
java -cp target/classes com.airtribe.meditrack.Main --loadData
```

On macOS/Linux:

```bash
java -cp target/classes com.airtribe.meditrack.Main --loadData
```

Run the demo TestRunner

```bash
# run TestRunner from compiled classes
java -cp target/classes com.airtribe.meditrack.test.TestRunner
```

Data and persistence
- CSV files are stored under the project `data/` directory when you call the `Exit` option in the console UI (persistence happens on exit).
- Expected CSV files: `data/patients.csv`, `data/doctors.csv`, `data/appointments.csv`.
- Loading existing CSVs: Start the app with `--loadData` to show counts; services automatically load persisted CSV records on startup.

Notes and troubleshooting
- Ensure `JAVA_HOME` points to a JDK 23 installation.
- If you change the Java version, update `pom.xml` and recompile.
- The CLI is minimal and expects ISO local datetime input for appointments, e.g. `2026-05-03T14:30`.# Setup Instructions (placeholder)

This file will contain step-by-step setup instructions for Java 23, Maven, and running the project. Screenshots and exact commands will be added.

Minimum required:
- Java 23 (JDK)
- Maven 3.8+

Example run (after building):

```bash
mvn compile
java -cp target/classes com.airtribe.meditrack.Main
```
