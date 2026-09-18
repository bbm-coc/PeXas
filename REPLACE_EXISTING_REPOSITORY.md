# Replace the old GitHub repository contents

This package is a clean replacement for the earlier Pexas GitHub build package.

## Recommended method

For the least confusion, use a new empty GitHub repository, then upload the extracted contents of this ZIP to the repository root.

If you want to keep the existing repository, remove the old project files first and then upload these contents. Do not upload the ZIP file itself.

The repository root should contain:

- `.github/workflows/build-apk.yml`
- `app/`
- `.gitattributes`
- `.gitignore`
- `LICENSE`
- `NOTICE`
- `README.md`
- `BUILD_ON_GITHUB.md`
- `REPOSITORY_FILES.txt`
- `build.gradle.kts`
- `gradle.properties`
- `settings.gradle.kts`

## After upload

Open **Actions**. The workflow should be named **Build Android APK**.

Click **Run workflow** and select the `main` branch.

The workflow does not use `sdkmanager --update` and does not depend on a Gradle wrapper JAR. It installs Gradle 8.10 directly in GitHub Actions.
