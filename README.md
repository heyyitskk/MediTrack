# MediTrack (Clinic & Appointment Management)

Lightweight Java (Maven) console application for managing patients, doctors, appointments and simple billing.

Quick features
- Patient/Doctor CRUD via console UI
- Appointment creation/cancellation with observer notifications
- CSV-based persistence (data/ folder)
- Billing using Strategy + Factory patterns
- Design patterns: Singleton (`IdGenerator`), Factory (`BillFactory`), Observer (`AppointmentNotifier`), Strategy (`BillingStrategy`)

Quick start
1. See `docs/Setup_Instructions.md` for build/run steps.
2. Run `TestRunner` to execute a small demo program: `com.airtribe.meditrack.test.TestRunner`.

Project structure
- `src/main/java` — application sources
- `data/` — CSV persistence files (created when persisting)
- `docs/` — project documentation

Notes
- This project targets Java 23. See `pom.xml` for compiler settings.
