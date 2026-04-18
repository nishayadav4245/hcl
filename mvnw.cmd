@REM ----------------------------------------------------------------------------
@REM Licensed to the Apache Software Foundation (ASF) under one
@REM or more contributor license agreements.  See the NOTICE file
@REM distributed with this work for additional information
@REM regarding copyright ownership.  The ASF licenses this file
@REM to you under the Apache License, Version 2.0 (the
@REM "License"); you may not use this file except in compliance
@REM with the License.  You may obtain a copy of the License at
@REM
@REM    https://www.apache.org/licenses/LICENSE-2.0
@REM
@REM Unless required by applicable law or agreed to in writing,
@REM software distributed under the License is distributed on an
@REM "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
@REM KIND, either express or implied.  See the License for the
@REM specific language governing permissions and limitations
@REM under the License.
@REM ----------------------------------------------------------------------------

@REM ----------------------------------------------------------------------------
@REM Apache Maven Wrapper startup batch script, version 3.2.0
@REM ----------------------------------------------------------------------------

@IF "%__MVNW_ARG0__%"=="" (SET __MVNW_ARG0__=%~dpnx0) ELSE (SET __MVNW_ARG0__=%__MVNW_ARG0__% %~dpnx0)
@SET __MVNW_CMD__=
@SET __MVNW_ERROR__=
@SET __MVNW_PSMODULEP_SAVE=%PSModulePath%
@SET PSModulePath=
@FOR /F "usebackq tokens=1* delims==" %%A IN (`powershell -noprofile "& {[io.file]::ReadAllText($env:__MVNW_ARG0__) -}replace '(?<=jar ^& exit /b )(.*)','${1}0teleType0teleType' -}split '0teleType' | Select-Object -First 1;}"`) DO @(
  IF /I "%%A"=="SET " (CALL %%A%%B) ELSE IF /I "%%A"=="cmd " (CALL %%A %%B) ELSE (ECHO.%%A%%B)
)
@SET PSModulePath=%__MVNW_PSMODULEP_SAVE%
@SET __MVNW_PSMODULEP_SAVE=
@SET __MVNW_ARG0__=
@SET MVNW_USERNAME=
@SET MVNW_PASSWORD=
@IF NOT "%__MVNW_CMD__%"=="" (%__MVNW_CMD__% %*)
@echo Cannot start MvnW, unsupported JDK &  exit /b 1

SET MVNW_JAVA_COMMAND=java
@REM Determine the Java command to use to start the JVM.
@IF NOT "%JAVA_HOME%"=="" (
  IF EXIST "%JAVA_HOME%\bin\java.exe" (
    SET MVNW_JAVA_COMMAND="%JAVA_HOME%\bin\java.exe"
  ) ELSE (
    SET MVNW_JAVA_COMMAND="%JAVA_HOME%\bin\java"
  )
)

SET WRAPPER_JAR="%~dp0\.mvn\wrapper\maven-wrapper.jar"
SET WRAPPER_LAUNCHER=org.apache.maven.wrapper.MavenWrapperMain
SET WRAPPER_URL="https://repo.maven.apache.org/maven2/org/apache/maven/wrapper/maven-wrapper/3.2.0/maven-wrapper-3.2.0.jar"
SET WRAPPER_SHA_256=""

@REM Extension to allow automatically downloading the maven-wrapper.jar from Maven-central
@REM This allows using the maven wrapper in projects that prohibit checking in binary data.
IF NOT EXIST %WRAPPER_JAR% (
    IF NOT "%MVNW_REPOURL%" == "" (
        SET WRAPPER_URL="%MVNW_REPOURL%/org/apache/maven/wrapper/maven-wrapper/3.2.0/maven-wrapper-3.2.0.jar"
    )
    @REM Download the maven-wrapper.jar
    powershell -Command "&{"^
		"$webclient = new-object System.Net.WebClient;"^
		"if (-not ([string]::IsNullOrEmpty('%MVNW_USERNAME%') -and [string]::IsNullOrEmpty('%MVNW_PASSWORD%'))) {"^
		"$webclient.Credentials = new-object System.Net.NetworkCredential('%MVNW_USERNAME%', '%MVNW_PASSWORD%');"^
		"}"^
		"[Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12; $webclient.DownloadFile('%WRAPPER_URL%', '%WRAPPER_JAR%')"^
		"}"
    IF "%MVNW_VERBOSE%" == "true" (
        ECHO Finished downloading %WRAPPER_JAR%
    )
)

@REM Provide a "standardized" way to retrieve the CLI args that will
@REM temporary work://unixlike systems too.
SET MAVEN_CMD_LINE_ARGS=%*

%MVNW_JAVA_COMMAND% ^
  %JVM_CONFIG_MAVEN_PROPS% ^
  %MAVEN_OPTS% ^
  %MAVEN_DEBUG_OPTS% ^
  -classpath %WRAPPER_JAR% ^
  "-Dmaven.multiModuleProjectDirectory=%~dp0" ^
  %WRAPPER_LAUNCHER% %MAVEN_CONFIG% %*

IF ERRORLEVEL 1 goto error
goto end

:error
SET ERROR_CODE=1

:end
@endlocal & set ERROR_CODE=%ERROR_CODE%

IF NOT "%MAVEN_SKIP_RC%"=="" goto skipRcPost
@REM check for post script, once with legacy .bat ending and once with .cmd ending
IF EXIST "%USERPROFILE%\mavenrc_post.bat" CALL "%USERPROFILE%\mavenrc_post.bat"
IF EXIST "%USERPROFILE%\mavenrc_post.cmd" CALL "%USERPROFILE%\mavenrc_post.cmd"
:skipRcPost

@REM pause the script if MAVEN_BATCH_PAUSE is set to 'on'
IF "%MAVEN_BATCH_PAUSE%"=="on" pause

IF "%MAVEN_TERMINATE_CMD%"=="on" EXIT %ERROR_CODE%

cmd /C exit /B %ERROR_CODE%
