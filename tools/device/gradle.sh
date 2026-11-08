#!/usr/bin/env bash
# Serialised Gradle entry point for this low-memory (4 GB) machine.
# Every agent must build through this script: it holds a lock so only one
# Gradle/Robolectric process runs at a time.
#   tools/device/gradle.sh :app:assembleDebug
#   tools/device/gradle.sh :app:testDebugUnitTest --tests 'com.example.screenshots.FullAppScreenshotTest' -Pshots.only=home
set -uo pipefail
cd "$(dirname "$0")/../.."
LOCK=/c/Users/Edgli/AppData/Local/Temp/catholic-hermes-gradle.lock
for i in $(seq 1 3600); do mkdir "$LOCK" 2>/dev/null && break; [ $((i % 60)) -eq 0 ] && echo "waiting for gradle lock ($i s)" >&2; sleep 1; done
trap 'rmdir "$LOCK" 2>/dev/null || true' EXIT
export JAVA_HOME="${JAVA_HOME_OVERRIDE:-/c/Program Files/Amazon Corretto/jdk17.0.20_12}"
./gradlew --no-daemon --no-configuration-cache --max-workers=2 -Dorg.gradle.jvmargs="-Xmx1536m -Dfile.encoding=UTF-8" "$@"
