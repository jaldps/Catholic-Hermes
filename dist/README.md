# Install on Android

[Download CatholicGPT 1.4 preview APK](https://github.com/jaldps/Catholic-Hermes/raw/refs/heads/CatholicGPT/dist/CatholicGPT-preview.apk).

Open the downloaded `.apk` on your Android phone and allow installation from your browser if Android asks. Sign into GitHub first if the repository requires it. Look for **CatholicGPT** in the phone launcher.

This updates the previous CatholicGPT preview and retains its data. It installs alongside the historical Sancta Fides app, which has independent data. Package: `com.aistudio.sanctafides.qvmrpx.catholicgpt`; version: `1.4-CatholicGPT` (versionCode 5).

Change the font under **More → Font** (Más → Fuente / Mais → Fonte). Choose the identity default, Inter, Roboto or EB Garamond. Bible and Catechism follow that choice unless you select a local reader override.

To reproduce in the prepared environment:

```sh
source /workspace/.cloud-setup/env.sh
flock /tmp/gradle.lock gradle --no-daemon --max-workers=4 --no-configuration-cache -I tools/preview.gradle :app:assembleDebug
```

The original `sancta-fides-1.3.apk` remains unchanged. APK files install on Android, not iPhone. See [validation](../docs/review/catholicgpt/VALIDATION.md) for test results and limitations.
