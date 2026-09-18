# PeXas
# Pexas Accounting & Billing – Android

**Pexas Accounting & Billing** is a native Android accounting, billing, inventory, and business management application built with **Kotlin** and **Jetpack Compose**.

The project is designed for fast and simple day-to-day business accounting, billing, stock management, GST-related work, and reporting.

## Features

- 🏢 Company Management
- 👥 Party / Customer / Supplier Masters
- 📦 Item & Inventory Management
- 🧾 Sales & Purchase
- 💰 Payment & Receipt
- 📊 Account Books
- 📈 MIS & Business Reports
- 🧮 GST-related utilities and reports
- 🔄 Stock Transfer
- 🏭 Production & Unassemble
- 📝 Journal & Contra
- ↩️ Sales Return / Credit Note
- ↩️ Purchase Return / Debit Note
- 📋 Physical Stock
- 🔧 Material Issued / Received from Party
- 🌳 Transaction Add / Modify / List workflow
- 📱 Mobile-friendly dashboard
- 💾 Local Room / SQLite database
- ⚡ Native Android performance

## Transaction Modules

Each transaction module provides an expandable:

- **Add**
- **Modify**
- **List**

workflow.

Current transaction modules include:

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

- **Kotlin**
- **Jetpack Compose**
- **Android SDK**
- **Room Database**
- **SQLite**
- **Navigation Compose**
- **Kotlin Coroutines**
- **Material Design**

## Requirements

- Android Studio Ladybug or newer
- JDK 17
- Android SDK 35
- Minimum Android version: Android 8.0 (API 26)

## Project Structure

```text
Pexas Accounting & Billing
│
├── app/
│   └── src/
│       └── main/
│           ├── java/
│           │   └── com/pexas/accounting/
│           └── res/
│
├── .github/
│   └── workflows/
│       └── build-apk.yml
│
├── build.gradle.kts
├── settings.gradle.kts
└── README.md
