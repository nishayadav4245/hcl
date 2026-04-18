#!/bin/sh
# ----------------------------------------------------------------------------
# Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements.  See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership.  The ASF licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License.  You may obtain a copy of the License at
#
#    https://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing,
# software distributed under the License is distributed on an
# "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
# KIND, either express or implied.  See the License for the
# specific language governing permissions and limitations
# under the License.
# ----------------------------------------------------------------------------

# ----------------------------------------------------------------------------
# Apache Maven Wrapper startup batch script, version 3.2.0
# ----------------------------------------------------------------------------

if [ -z "$MAVEN_SKIP_RC" ] ; then

  if [ -f /usr/local/etc/mavenrc ] ; then
    . /usr/local/etc/mavenrc
  fi

  if [ -f /etc/mavenrc ] ; then
    . /etc/mavenrc
  fi

  if [ -f "$HOME/.mavenrc" ] ; then
    . "$HOME/.mavenrc"
  fi

fi

# OS specific support.
cygwin=false;
darwin=false;
mingw=false
case "$(uname)" in
  CYGWIN*) cygwin=true ;;
  MINGW*) mingw=true;;
  Darwin*) darwin=true
    if [ -z "$JAVA_VERSION" ] ; then
      JAVA_VERSION="1.8+"
    fi
    if [ -z "$JAVA_HOME" ] ; then
      JAVA_HOME=$(/usr/libexec/java_home --version ${JAVA_VERSION} 2> /dev/null)
    fi
    ;;
esac

MAVEN_HOME=$(cd "$(dirname "$0")" && pwd)

if [ -z "$JAVA_HOME" ] ; then
  JAVACMD=$(which java)
else
  JAVACMD="$JAVA_HOME/bin/java"
fi

if [ ! -x "$JAVACMD" ] ; then
  echo "The JAVA_HOME environment variable is not defined correctly," >&2
  echo "this environment variable is needed to run this program." >&2
  exit 1
fi

CLASSWORLDS_JAR=$(echo "$MAVEN_HOME"/.mvn/wrapper/maven-wrapper*.jar)
MAVEN_PROJECTBASEDIR=${MAVEN_BASEDIR:-"$MAVEN_HOME"}

WRAPPER_LAUNCHER=org.apache.maven.wrapper.MavenWrapperMain

if [ ! -f "$CLASSWORLDS_JAR" ] ; then
  wrapperUrl="https://repo.maven.apache.org/maven2/org/apache/maven/wrapper/maven-wrapper/3.2.0/maven-wrapper-3.2.0.jar"
  if [ "$MVNW_VERBOSE" = true ]; then
    echo "Downloading from: $wrapperUrl"
  fi
  if $cygwin; then
    CLASSWORLDS_JAR=$(cygpath --path --windows "$CLASSWORLDS_JAR")
  fi

  if command -v wget > /dev/null; then
    if [ "$MVNW_VERBOSE" = true ]; then
      echo "Found wget ... using wget"
    fi
    if [ -z "$MVNW_USERNAME" ] || [ -z "$MVNW_PASSWORD" ]; then
      wget "$wrapperUrl" -O "$CLASSWORLDS_JAR" || rm -f "$CLASSWORLDS_JAR"
    else
      wget --http-user="$MVNW_USERNAME" --http-password="$MVNW_PASSWORD" "$wrapperUrl" -O "$CLASSWORLDS_JAR" || rm -f "$CLASSWORLDS_JAR"
    fi
  elif command -v curl > /dev/null; then
    if [ "$MVNW_VERBOSE" = true ]; then
      echo "Found curl ... using curl"
    fi
    if [ -z "$MVNW_USERNAME" ] || [ -z "$MVNW_PASSWORD" ]; then
      curl -o "$CLASSWORLDS_JAR" "$wrapperUrl" -f
    else
      curl --user "$MVNW_USERNAME:$MVNW_PASSWORD" -o "$CLASSWORLDS_JAR" "$wrapperUrl" -f
    fi
  else
    if [ "$MVNW_VERBOSE" = true ]; then
      echo "Falling back to using Java to download"
    fi
    javaSource="$MAVEN_PROJECTBASEDIR/.mvn/wrapper/MavenWrapperDownloader.java"
    javaClass="$MAVEN_PROJECTBASEDIR/.mvn/wrapper/MavenWrapperDownloader.class"
    if [ -e "$javaSource" ]; then
      if [ ! -e "$javaClass" ]; then
        if [ "$MVNW_VERBOSE" = true ]; then
          echo " - Compiling MavenWrapperDownloader.java ..."
        fi
        ("$JAVA_HOME/bin/javac" "$javaSource")
      fi
      if [ -e "$javaClass" ]; then
        if [ "$MVNW_VERBOSE" = true ]; then
          echo " - Running MavenWrapperDownloader.java ..."
        fi
        ("$JAVA_HOME/bin/java" -cp .mvn/wrapper MavenWrapperDownloader "$MAVEN_PROJECTBASEDIR")
      fi
    fi
  fi
fi

exec "$JAVACMD" \
  $MAVEN_OPTS \
  $MAVEN_DEBUG_OPTS \
  -classpath "$CLASSWORLDS_JAR" \
  "-Dmaven.multiModuleProjectDirectory=${MAVEN_PROJECTBASEDIR}" \
  ${WRAPPER_LAUNCHER} $MAVEN_CONFIG "$@"
