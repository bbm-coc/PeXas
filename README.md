# Pexas Accounting & Billing — Native Android

Version: **1.0.1**

This is a native Android starter project written in Kotlin + Jetpack Compose. It is designed from the uploaded menu screenshots and provides a mobile structure for accounting, billing, inventory, masters and reports.

## Included from the uploaded menu structure

### Company
- Open Company
- Create Company
- Backup Data
- Restore Data
- Split Financial Year
- Delete Company / Single F.Y.
- Reindex Databases

### Administration / Masters
- Account
- Account Group
- Std. Narration
- Item
- Item Group
- Material Centre
- Material Centre Group
- Unit
- Unit Conversion
- Bill Sundry
- Bill of Material
- Sale Type
- Purchase Type
- Tax Category
- Misc. Masters
- Configuration
- Users
- Utilities
- Bulk Updations
- Data Export / Import
- Miscellaneous Data Entry
- Change Financial Year

### Transactions
- Sales Quotation
- Sales Order
- Sales: Add / Modify / List
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
- Material Issued to Party
- Material Received from Party
- Physical Stock
- GST Misc. Utilities

### Display / Reports
- Final Results
- Trial Balance
- Account Books
- Day Book
- Account Ledger
- Cash/Bank Book
- Account Registers
- Account Activity Report
- Party Day Book
- Account Summary
- Outstanding Analysis
- Interest Calculation
- Depreciation Chart
- Bank Reconciliation
- Stock Status
- Inventory Books
- Inventory Summary
- MRP/Parameter-wise Reports
- Quotation/Order/Challan Processing
- GST Reports
- MIS Reports
- Check List
- Sales Analysis
- Purchase Analysis
- Issue-Receipt Analysis
- Consumption Analysis
- Item Receivable/Issuable

## Data layer
The starter uses Room/SQLite and includes Company, Party, Item, Sales and Purchase entities. It is offline-first and can be extended with the remaining accounting ledgers and voucher tables.

## Open in Android Studio
1. Extract the ZIP.
2. Open the extracted folder in Android Studio.
3. Let Gradle sync.
4. Run the `app` configuration on an Android device/emulator.

## Next production modules
- Full double-entry ledger engine
- Voucher numbering and audit trail
- GST invoice calculation and tax reports
- PDF invoice/ledger printing and sharing
- Excel/CSV import with preview + validation + rollback
- Company backup/restore
- User permissions
- Multi-device synchronization/API
- Barcode/QR scanning
- Payment/receipt reconciliation
- Challan and stock workflows
- Search across invoice, bill, party, item, phone and voucher numbers

## Changelog
### 1.0.1
- Added an expandable Add / Modify / List tree under every transaction.
- Added the full transaction menu from the uploaded reference.

### 1.0.0
- Added the first native Android project.
- Added mobile navigation for the main accounting and billing sections.
- Added Company, Party and Item masters.
- Added basic Sales and Purchase entry.
- Added local Room database storage.
- Added the menu/report structure shown in the uploaded references.


## Fixes in v1.0.1-corrected (GitHub Actions build)

- Included official `gradle-wrapper.jar` and standard `gradlew` scripts.
- Added Java 17 `compileOptions` + Kotlin `jvmToolchain(17)`.
- Updated workflow to avoid deprecated Android SDK `tools` package.
- Added `strings.xml` and cleaned manifest/theme.

## Build APK with GitHub Actions

The project includes `.github/workflows/build-apk.yml`.

1. Create a GitHub repository.
2. Upload the **contents of this project** to the repository.
3. Open the repository's **Actions** tab.
4. Select **Build Android APK**.
5. Click **Run workflow**.
6. Wait for the workflow to finish.
7. Open the completed workflow run.
8. Under **Artifacts**, download `pexas-accounting-billing-debug-apk`.
9. Extract the artifact to get `app-debug.apk`.
