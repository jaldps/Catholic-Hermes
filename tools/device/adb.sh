#!/usr/bin/env bash
# Phone helpers for the redesign loop (one agent drives the phone at a time).
#   tools/device/adb.sh shot <out.png>     screenshot to a local file
#   tools/device/adb.sh tap <x> <y>        tap physical pixels (1220x2712)
#   tools/device/adb.sh tapText <text>     tap the first node whose text/desc contains <text>
#   tools/device/adb.sh swipe x1 y1 x2 y2  swipe (scroll)
#   tools/device/adb.sh back | dump | launch | install <apk>
set -euo pipefail
export MSYS_NO_PATHCONV=1
ADB="${ADB:-/c/Users/Edgli/AppData/Local/Android/Sdk/platform-tools/adb.exe}"
PKG=com.aistudio.sanctafides.qvmrpx
LOCK=/c/Users/Edgli/AppData/Local/Temp/catholic-hermes-phone.lock
for i in $(seq 1 600); do mkdir "$LOCK" 2>/dev/null && break; sleep 1; done
trap 'rmdir "$LOCK" 2>/dev/null || true' EXIT
dump() {
  "$ADB" shell uiautomator dump /sdcard/ui.xml >/dev/null 2>&1
  "$ADB" exec-out cat /sdcard/ui.xml | sed 's/></>\n</g' \
    | sed -nE 's/.*text="([^"]*)".*content-desc="([^"]*)".*bounds="\[([0-9]+),([0-9]+)\]\[([0-9]+),([0-9]+)\]".*/\1\2|\3 \4 \5 \6/p' \
    | grep -v '^|' || true
}
case "$1" in
  shot) "$ADB" shell screencap -p /sdcard/shot.png && "$ADB" pull -q /sdcard/shot.png "$(cygpath -w "$2")" >/dev/null && "$ADB" shell rm /sdcard/shot.png && echo "$2" ;;
  tap) "$ADB" shell input tap "$2" "$3" ;;
  swipe) "$ADB" shell input swipe "$2" "$3" "$4" "$5" "${6:-400}" ;;
  back) "$ADB" shell input keyevent 4 ;;
  dump) dump ;;
  tapText)
    line=$(dump | grep -iF -- "$2" | head -1 || true)
    [ -z "$line" ] && { echo "not found: $2" >&2; exit 1; }
    read -r x1 y1 x2 y2 <<<"${line##*|}"
    "$ADB" shell input tap $(( (x1+x2)/2 )) $(( (y1+y2)/2 )) ;;
  launch) "$ADB" shell am force-stop $PKG; "$ADB" shell monkey -p $PKG -c android.intent.category.LAUNCHER 1 >/dev/null 2>&1; sleep 3 ;;
  install) "$ADB" install -r "$(cygpath -w "$2")" ;;
  *) echo "unknown command $1" >&2; exit 2 ;;
esac
