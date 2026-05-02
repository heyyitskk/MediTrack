# Design Decisions

Persistence
- CSV files: chosen for simplicity, portability, and to meet the assignment's CSV-only persistence requirement. Implemented in `PersistenceManager` and `CSVUtil`.

Patterns used
- Singleton: `IdGenerator` ensures globally unique id generation.
- Factory: `BillFactory` centralizes bill creation and tax handling.
- Observer: `AppointmentNotifier` + `AppointmentListener` implements notifications when appointments are created or cancelled.
- Strategy: `BillingStrategy` allows different billing adjustments (standard vs discount) and is wired into the `BillFactory`.

Cloning
- `Patient.clone()` and `Appointment.clone()` create new objects with fresh ids (to avoid id collisions) and copy primitive/immutable fields. Deep cloning was not required because fields are either primitives or immutable (`String`, `LocalDateTime`). If mutable nested objects are added, implement manual deep copy.

Thread-safety
- The `DataStore` is a simple `HashMap` wrapper and is not synchronized. The application is single-threaded (console UI); if multi-threading is added, `DataStore` should be updated to a concurrent collection or synchronized.

Limitations & future work
- Replace CSV persistence with a real DB for scalability.
- Add unit/integration tests and CI workflow.
- Improve CLI with argument parsing and better validation.
- Add export/import and data migration utilities.
