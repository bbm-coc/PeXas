# Build Pexas Accounting & Billing on GitHub

1. Upload the complete project contents to the repository root.
2. Confirm these folders exist at the root:
   - `.github/workflows/build-apk.yml`
   - `app/`
   - `gradle/wrapper/`
3. Confirm these files exist at the root:
   - `gradlew`
   - `gradlew.bat`
4. Open **Actions** on GitHub.
5. Select **Build Android APK**.
6. Click **Run workflow**.
7. When the workflow finishes, open the run and download `pexas-accounting-billing-debug-apk`.

The Gradle wrapper is configured for Gradle 8.10. If the wrapper JAR is not already present, the included wrapper scripts download the official Gradle wrapper JAR on first use.
