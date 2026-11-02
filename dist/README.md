# Install on Android

[Download CatholicGPT preview APK](https://github.com/jaldps/Catholic-Hermes/raw/refs/heads/CatholicGPT/dist/CatholicGPT-preview.apk).

Open the downloaded `.apk` on your Android phone and allow installation from your browser if Android asks. Sign into GitHub first if the repository requires it.

This preview installs alongside the historical Sancta Fides app. It has separate app data, so you can keep the old installation and its progress. Package: `com.aistudio.sanctafides.qvmrpx.catholicgpt`; version: `1.3-CatholicGPT`.

To reproduce: activate the documented development environment and run:

```sh
flock /tmp/gradle.lock gradle --no-daemon --max-workers=4 --no-configuration-cache -I tools/preview.gradle :app:assembleDebug
```

The original `sancta-fides-1.3.apk` remains unchanged. APK files install on Android, not iPhone.
