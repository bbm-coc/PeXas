@ECHO OFF
SETLOCAL
SET APP_HOME=%~dp0
SET WRAPPER_JAR=%APP_HOME%gradle\wrapper\gradle-wrapper.jar
IF NOT EXIST "%WRAPPER_JAR%" (
  IF EXIST "%SystemRoot%\System32\curl.exe" (
    "%SystemRoot%\System32\curl.exe" -fL -o "%WRAPPER_JAR%" "https://raw.githubusercontent.com/gradle/gradle/v8.10.2/gradle/wrapper/gradle-wrapper.jar"
  ) ELSE (
    powershell -NoProfile -ExecutionPolicy Bypass -Command "Invoke-WebRequest -UseBasicParsing -Uri 'https://raw.githubusercontent.com/gradle/gradle/v8.10.2/gradle/wrapper/gradle-wrapper.jar' -OutFile '%WRAPPER_JAR%'"
  )
)
IF NOT EXIST "%WRAPPER_JAR%" (
  ECHO Failed to obtain gradle-wrapper.jar
  EXIT /B 1
)
java %JAVA_OPTS% %GRADLE_OPTS% -classpath "%WRAPPER_JAR%" org.gradle.wrapper.GradleWrapperMain %*
ENDLOCAL
