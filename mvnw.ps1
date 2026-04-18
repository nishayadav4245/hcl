$ErrorActionPreference = "Stop"

$MAVEN_PROJECTBASEDIR = $PSScriptRoot
$WRAPPER_PROPERTIES = Join-Path $MAVEN_PROJECTBASEDIR ".mvn\wrapper\maven-wrapper.properties"
$WRAPPER_JAR = Join-Path $MAVEN_PROJECTBASEDIR ".mvn\wrapper\maven-wrapper.jar"
$WRAPPER_URL = "https://repo.maven.apache.org/maven2/org/apache/maven/wrapper/maven-wrapper/3.2.0/maven-wrapper-3.2.0.jar"

if (-not (Test-Path $WRAPPER_JAR)) {
    Write-Host "Downloading Maven Wrapper..."
    New-Item -ItemType Directory -Force -Path (Split-Path $WRAPPER_JAR) | Out-Null
    [Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12
    Invoke-WebRequest -Uri $WRAPPER_URL -OutFile $WRAPPER_JAR
    Write-Host "Maven Wrapper downloaded."
}

$JAVA_CMD = "java"
if ($env:JAVA_HOME) {
    $JAVA_CMD = Join-Path $env:JAVA_HOME "bin\java.exe"
}

$arguments = @(
    "-classpath", $WRAPPER_JAR,
    "-Dmaven.multiModuleProjectDirectory=$MAVEN_PROJECTBASEDIR",
    "org.apache.maven.wrapper.MavenWrapperMain"
) + $args

& $JAVA_CMD @arguments
exit $LASTEXITCODE
