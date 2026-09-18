# Build Pexas on GitHub

1. Upload the contents of this repository to the root of your GitHub repository.
2. Make sure `.github/workflows/build-apk.yml` is present.
3. Open **Actions**.
4. Select **Build Android APK**.
5. Click **Run workflow**.
6. After a successful run, download the artifact named `pexas-accounting-billing-debug-apk`.

The workflow does not use `sdkmanager --update`, because current Android command-line tools no longer provide the old `tools` package.

The workflow also installs Gradle 8.10 directly through `gradle/actions/setup-gradle`, so a Gradle wrapper JAR is not required for the GitHub build.
