# Build APK on GitHub Actions

This project is ready to build a debug APK using GitHub Actions.

## Steps

1. Create a new GitHub repository.
2. Upload **all files and folders** from this project (including the hidden `.github` folder and `gradle/wrapper/gradle-wrapper.jar`).
3. Open the repository **Actions** tab.
4. Select the workflow **Build Android APK**.
5. Click **Run workflow** → **Run workflow**.
6. Wait for the job to finish (usually 3–8 minutes).
7. Open the completed run.
8. Under **Artifacts**, download `pexas-accounting-billing-debug-apk`.
9. Extract the zip to get `app-debug.apk`.

## What was fixed in this corrected package

- Official Gradle wrapper JAR and scripts are included (no runtime download required).
- Java 17 / Kotlin JVM toolchain configured for AGP 8.7.
- GitHub Actions workflow no longer requests the deprecated Android SDK `tools` package.
- Minimal `strings.xml` and cleaner manifest added.

## Local build (optional)

```bash
chmod +x gradlew
./gradlew assembleDebug
```

APK will be at: `app/build/outputs/apk/debug/app-debug.apk`
