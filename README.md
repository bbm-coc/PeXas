# Pexas Accounting & Billing — Native Android

**Pexas Accounting & Billing** is a native Android accounting, billing, inventory and reporting application built with **Kotlin** and **Jetpack Compose**.

## Version

**1.0.2**

### Changelog

#### 1.0.2
- Rebuilt the repository as a clean GitHub-ready Android project.
- Fixed the GitHub Actions Android SDK setup so it does not run the obsolete `sdkmanager --update` command.
- Updated the Java setup action to the current v5 release.
- GitHub Actions installs the required Gradle version directly, so the build does not depend on a missing Gradle wrapper JAR.
- Fixed the dashboard action-card layout.
- Kept the transaction Add / Modify / List tree for every transaction.

#### 1.0.1
- Added an expandable Add / Modify / List tree under every transaction.
- Added the full transaction menu from the reference structure.

#### 1.0.0
- Added the initial native Android project.
- Added Kotlin + Jetpack Compose foundation.
- Added company, party and item masters.
- Added basic sales and purchase data entry.
- Added Room / SQLite local database foundation.

## Included modules

- Company management
- Administration / Masters
- Transactions
- Account Books
- Inventory
- GST Reports
- MIS Reports
- Print / Email / SMS menu structure
- House-Keeping / Utilities
- Local Room / SQLite database

## Transaction Add / Modify / List

Every transaction in the transaction tree expands to:

- Add
- Modify
- List

Current transactions:

- Sales Quotation
- Sales Order
- Sales
- Purchase Order
- Purchase
- Sales Return (Cr. Note)
- Purchase Return (Dr. Note)
- Payment
- Receipt
- Journal
- Contra
- Dr. Note (w/o Items)
- Cr. Note (w/o Items)
- Stock Transfer
- Production
- Unassemble
- Stock Journal
- Mat. Issued to Party
- Mat. Rcvd. from Party
- Physical Stock
- GST Misc. Utilities

## Technology

- Kotlin 2.0.21
- Jetpack Compose
- Android Gradle Plugin 8.7.3
- Gradle 8.10 for GitHub Actions
- Android SDK 35
- Minimum Android 8.0 / API 26
- Room 2.6.1
- SQLite
- Navigation Compose
- Kotlin Coroutines

## GitHub Actions APK build

The repository includes:

`.github/workflows/build-apk.yml`

The workflow:

1. Checks out the repository.
2. Sets up JDK 17.
3. Sets up the Android SDK.
4. Accepts Android SDK licenses.
5. Installs only the required Android SDK packages.
6. Sets up Gradle 8.10 directly.
7. Builds the debug APK.
8. Uploads the APK as a GitHub Actions artifact.

### Build steps

1. Open the repository on GitHub.
2. Select **Actions**.
3. Select **Build Android APK**.
4. Click **Run workflow**.
5. Wait for the build to finish.
6. Open the successful workflow run.
7. Download **pexas-accounting-billing-debug-apk** from Artifacts.

## Project structure

```text
Pexas/
├── .github/
│   └── workflows/
│       └── build-apk.yml
├── app/
│   ├── build.gradle.kts
│   └── src/main/
│       ├── AndroidManifest.xml
│       ├── java/com/pexas/accounting/
│       └── res/values/themes.xml
├── .gitattributes
├── .gitignore
├── LICENSE
├── NOTICE
├── README.md
├── build.gradle.kts
├── gradle.properties
└── settings.gradle.kts
```

> This GitHub build intentionally does not require `gradlew` or a Gradle wrapper JAR. GitHub Actions installs Gradle 8.10 directly using the official Gradle setup action.

## Database

The local Room database currently contains foundations for:

- Company
- Party
- Item
- Sales
- Purchase

Additional accounting, inventory, GST and voucher tables can be added as development continues.

## Development status

**Active development.**

The current project is a working native Android foundation. Full production accounting ledgers, voucher numbering, GST calculations, printing, backup/restore, import/export, permissions and additional workflows can be implemented progressively.

## License

Apache License 2.0. See `LICENSE` for the complete license text.

## Author

**Pexas**
