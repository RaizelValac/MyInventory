Summary: An Android inventory management application designed for small businesses. It features real-time inventory tracking, automated background backups, and CSV data export.

Tech Stack:

Language: Kotlin

UI: Jetpack Compose (Material 3)

Architecture: MVVM + Clean Architecture

Dependency Injection: Dagger Hilt

Local Database: Room (SQLite)

Background Processing: WorkManager (Daily Auto-Backup)

Asynchronous: Coroutines & Kotlin Flow

Key Features:

Reactive UI updates using StateFlow.

Thread-safe File I/O for CSV exporting.

Dependency Injection for testability.

