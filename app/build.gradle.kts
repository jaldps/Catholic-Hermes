plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.compose)
  alias(libs.plugins.google.devtools.ksp)
}

android {
  namespace = "com.example"
  compileSdk { version = release(36) { minorApiLevel = 1 } }

  defaultConfig {
    applicationId = "com.aistudio.sanctafides.qvmrpx"
    minSdk = 24
    targetSdk = 36
    versionCode = 8
    versionName = "1.7"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  // Do not remove or modify these signingConfigs. They are necessary for building, installing,
  // and updating Android apps in AI Studio.
  signingConfigs {
    create("release") {
      val keystorePath = System.getenv("KEYSTORE_PATH") ?: "${rootDir}/my-upload-key.jks"
      storeFile = file(keystorePath)
      storePassword = System.getenv("STORE_PASSWORD")
      keyAlias = "upload"
      keyPassword = System.getenv("KEY_PASSWORD")
    }
    create("debugConfig") {
      storeFile = file("${rootDir}/debug.keystore")
      storePassword = "android"
      keyAlias = "androiddebugkey"
      keyPassword = "android"
    }
  }

  buildTypes {
    release {
      isCrunchPngs = false
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
      // Do not remove or modify this signingConfig assignment. It is necessary for Android apps in
      // AI Studio.
      signingConfig = signingConfigs.getByName("release")
    }
    debug {
      // Do not remove or modify this signingConfig assignment. It is necessary for Android apps in
      // AI Studio.
      signingConfig = signingConfigs.getByName("debugConfig")
    }
  }
  compileOptions {
    isCoreLibraryDesugaringEnabled = true
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }
  buildFeatures {
    compose = true
    buildConfig = true
  }
  testOptions {
    unitTests {
      isIncludeAndroidResources = true
      all { test ->
        // Lets a screenshot run be narrowed: -Pshots.only=home,bible
        listOf("shots.only", "shots.lang", "shots.font", "shots.scale", "shots.zone", "shots.width", "shots.height", "shots.identity", "shots.mode").forEach { key ->
          project.findProperty(key)?.let { test.systemProperty(key, it) }
        }
        test.maxHeapSize = "3g"
        // Native Robolectric/Compose window state must not cross test classes.
        // Each class still executes every test and all normal idleness checks.
        test.forkEvery = 1L
      }
    }
  }
  dependenciesInfo {
    includeInApk = false
    includeInBundle = true
  }
}

// JVM tests read the same immutable novena assets as Android.
android.sourceSets.getByName("test").resources.srcDir("src/main/assets/novenas")
android.sourceSets.getByName("test").resources.srcDir("src/main/assets/prayers")
android.sourceSets.getByName("test").resources.srcDir("src/main/assets/questions")
android.sourceSets.getByName("test").resources.srcDir("src/main/assets/saints")
android.sourceSets.getByName("test").resources.srcDir("src/main/assets/orders")
android.sourceSets.getByName("test").resources.srcDir("src/main/assets/devotions")
android.sourceSets.getByName("test").resources.srcDir("src/main/assets/courses")
android.sourceSets.getByName("test").resources.srcDir("src/main/assets/confession")

// Some unused dependencies are commented out below instead of being removed.
// This makes it easy to add them back in the future if needed.
dependencies {
  coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.1.5")
  implementation(platform(libs.androidx.compose.bom))
  // implementation(libs.accompanist.permissions)
  implementation(libs.androidx.activity.compose)
  // implementation(libs.androidx.camera.camera2)
  // implementation(libs.androidx.camera.core)
  // implementation(libs.androidx.camera.lifecycle)
  // implementation(libs.androidx.camera.view)
  implementation(libs.androidx.compose.material.icons.core)
  implementation(libs.androidx.compose.material.icons.extended)
  implementation(libs.androidx.compose.material3)
  implementation(libs.androidx.compose.ui)
  implementation(libs.androidx.compose.ui.graphics)
  implementation(libs.androidx.compose.ui.tooling.preview)
  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.datastore.preferences)
  implementation(libs.androidx.lifecycle.runtime.compose)
  implementation(libs.androidx.lifecycle.runtime.ktx)
  implementation(libs.androidx.lifecycle.viewmodel.compose)
  implementation(libs.androidx.room.ktx)
  implementation(libs.androidx.room.runtime)
  implementation(libs.coil.compose)
  // Uncomment to use Firestore:
  // implementation(libs.firebase.firestore)

  // Uncomment ALL FOUR of the following dependencies together to use Firebase Auth and Google
  // Sign-In via Credential Manager:
  // implementation(libs.firebase.auth)
  // implementation(libs.androidx.credentials)
  // implementation(libs.androidx.credentials.play.services)
  // implementation(libs.googleid)
  implementation(libs.kotlinx.coroutines.android)
  implementation(libs.kotlinx.coroutines.core)
  implementation(libs.logging.interceptor)
  implementation(libs.moshi.kotlin)
  implementation(libs.okhttp)
  constraints {
    // These versions were already selected through the removed Navigation/Firebase SDKs.
    // Keep the effective UI/network versions rather than downgrading their transitive users.
    listOf(
      "androidx.collection:collection:1.4.4",
      "androidx.concurrent:concurrent-futures:1.2.0",
      "androidx.compose.animation:animation:1.7.2",
      "androidx.compose.foundation:foundation:1.7.2",
      "androidx.compose.runtime:runtime:1.7.2",
      "androidx.compose.ui:ui:1.7.2"
    ).forEach { coordinate ->
      implementation(coordinate) {
        because("Preserve the resolved version used before removing unused SDKs")
      }
    }
    implementation("com.squareup.okio:okio:3.9.1") {
      because("Preserve the version already used before removing unused SDKs")
    }
  }
  // implementation(libs.play.services.location)
  testImplementation(libs.androidx.compose.ui.test.junit4)
  testImplementation(libs.androidx.core)
  testImplementation(libs.androidx.junit)
  testImplementation(libs.junit)
  testImplementation(libs.kotlinx.coroutines.test)
  testImplementation(libs.robolectric)
  androidTestImplementation(platform(libs.androidx.compose.bom))
  androidTestImplementation(libs.androidx.compose.ui.test.junit4)
  androidTestImplementation(libs.androidx.espresso.core)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.runner)
  debugImplementation(libs.androidx.compose.ui.test.manifest)
  debugImplementation(libs.androidx.compose.ui.tooling)
  "ksp"(libs.androidx.room.compiler)
  "ksp"(libs.moshi.kotlin.codegen)
}

ksp {
  arg("room.schemaLocation", "$projectDir/schemas")
}
