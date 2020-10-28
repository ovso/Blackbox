import org.ajoberstar.grgit.Grgit
import org.ajoberstar.grgit.service.TagService
import org.jetbrains.kotlin.konan.properties.Properties
import java.io.FileInputStream

plugins {
  id("com.android.application")
  kotlin("android")
  id("kotlin-android-extensions")
  id("kotlin-kapt")
  id("com.google.gms.google-services")
  id("com.google.android.gms.oss-licenses-plugin")
//  id("dagger.hilt.android.plugin")
  id("kotlin-android")
  id("org.ajoberstar.grgit") version "4.0.2"
  id("com.google.firebase.crashlytics")
  id("androidx.navigation.safeargs.kotlin")
}

val grGit: Grgit = Grgit.open(mapOf("currentDir" to project.rootDir))
fun getVersionName(grGit: Grgit): String {
  return TagService(grGit.repository).list().last().name
}

fun getVersionCode(grGit: Grgit): Int {
  return TagService(grGit.repository).list().size
}

val keystorePropertiesFile =
  rootProject.file("../../workspace_side/jks/blackbox_keystore.properties")
val keystoreProperties = Properties()
keystoreProperties.load(FileInputStream(keystorePropertiesFile))

android {
  compileSdkVersion(Config.compileSdk)
  defaultConfig {
    applicationId = Config.appId
    minSdkVersion(Config.minSdk)
    targetSdkVersion(Config.targetSdk)
    versionCode = Config.versionCode
    val versionMajor = 0
    val versionMinor = 0
    val versionPatch = versionCode

    versionName = "$versionMajor.$versionMinor.$versionPatch"
    testInstrumentationRunner = "androidx.test.ext.junit.runners.AndroidJUnit4"
    println("versionName = $versionName")
    println("versionCode = $versionCode")
    setProperty("archivesBaseName", "blackbox-$versionName")
    //multiDexEnabled true
  }

  bundle {
    density.enableSplit = true
    abi.enableSplit = true
    language.enableSplit = true
  }

  signingConfigs {
    getByName("debug") {

    }

    create("release") {
      keyAlias = keystoreProperties.getProperty("keyAlias")
      keyPassword = keystoreProperties.getProperty("keyPassword")
      storeFile = file(keystoreProperties.getProperty("storeFile"))
      storePassword = keystoreProperties.getProperty("storePassword")
    }
  }

  buildTypes {

    getByName("debug") {

    }

    getByName("release") {
      signingConfig = signingConfigs.getByName("release")
      isDebuggable = false
      isZipAlignEnabled = true
      isMinifyEnabled = false // 개발할때
      proguardFile(getDefaultProguardFile("proguard-android.txt"))
      // global proguard settings
      proguardFile(file("proguard-rules.pro"))
      // library proguard settings
      val files = rootProject.file("proguard")
        .listFiles()
        ?.filter { it.name.startsWith("proguard") }
        ?.toTypedArray()

      proguardFiles(*files ?: arrayOf())
    }
  }

  buildFeatures {
    viewBinding = true
  }

  androidExtensions {
    isExperimental = true
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }

  kotlinOptions {
    jvmTarget = "1.8"
  }

  lintOptions {
    disable("MissingTranslation")
  }

  packagingOptions {
    exclude("META-INF/kotlinx-io.kotlin_module")
    exclude("META-INF/atomicfu.kotlin_module")
    exclude("META-INF/kotlinx-coroutines-io.kotlin_module")
  }

}

dependencies {
  implementation(
    fileTree(
      mapOf(
        "dir" to "libs",
        "include" to listOf("*.jar"),
        "include" to listOf("*.aar")
      )
    )
  )

  implementation(project(":commons"))
  implementation(project(":nativetemplates"))
  implementation(files("libs/YouTubeAndroidPlayerApi.jar"))

  // coroutines
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutine_core}")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutine_android}")

  // kotlin
  implementation("org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}")
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}")

  // support library
  implementation("androidx.appcompat:appcompat:${Versions.appcompat}")
  implementation("com.google.android.material:material:${Versions.material}")

  // view
  implementation("androidx.viewpager2:viewpager2:${Versions.viewpager2}")
  implementation("androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}")

  // annotation
  implementation("androidx.annotation:annotation:1.1.0")

  // lifecycle
  implementation("androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}")
  implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}")
  implementation("androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}")
  implementation("androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle}")
  implementation("androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}")

  // activity
  implementation("androidx.activity:activity-ktx:1.1.0")

  // fragment
  implementation("androidx.fragment:fragment-ktx:${Versions.fragment_ktx}")

  // navigation
  implementation("androidx.navigation:navigation-ui-ktx:${Versions.navigation}")
  implementation("androidx.navigation:navigation-fragment-ktx:${Versions.navigation}")

  // ktx
  implementation("androidx.core:core-ktx:${Versions.core_ktx}")

  // startup
  implementation("androidx.startup:startup-runtime:${Versions.startup}")


  // firebase
  implementation(platform("com.google.firebase:firebase-bom:${Versions.firebase_bom}"))
  implementation("com.google.firebase:firebase-ads")
  implementation("com.google.firebase:firebase-crashlytics-ktx")
  implementation("com.google.firebase:firebase-analytics-ktx")

  // dagger
  implementation("com.google.dagger:dagger:${Versions.dagger}")

  kapt("com.google.dagger:dagger-compiler:${Versions.dagger}")
  implementation("com.google.dagger:dagger-android:${Versions.dagger_android}")
  implementation("com.google.dagger:dagger-android-support:${Versions.dagger_android}")
  kapt("com.google.dagger:dagger-android-processor:${Versions.dagger_android}")

  // dagger hilt
/*
  implementation("com.google.dagger:hilt-android:${Versions.hilt_android}")
  kapt("com.google.dagger:hilt-android-compiler:${Versions.hilt_android}")
  implementation("androidx.hilt:hilt-common:${Versions.hilt_viewmodel}")
  implementation("androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hilt_viewmodel}")
  kapt("androidx.hilt:hilt-compiler:${Versions.hilt_viewmodel}")
*/

  // image
  implementation("com.github.bumptech.glide:glide:${Versions.glide}")
  kapt("com.github.bumptech.glide:compiler:${Versions.glide}")

  // rx
  implementation("io.reactivex.rxjava3:rxjava:${Versions.rxjava3}")
  implementation("io.reactivex.rxjava3:rxandroid:${Versions.rxjava3_rxandroid}")
  implementation("io.reactivex.rxjava3:rxkotlin:${Versions.rxjava3_rxkotlin}")
  implementation("com.uber.autodispose2:autodispose:${Versions.autodispose}")

  // log
  implementation("com.jakewharton.timber:timber:${Versions.timber}")
  implementation("com.orhanobut:logger:${Versions.logger}")

  // network
  implementation("com.squareup.retrofit2:retrofit:${Versions.retrofit}")
  implementation("com.squareup.retrofit2:converter-gson:${Versions.retrofit}")
  implementation("com.squareup.okhttp3:logging-interceptor:${Versions.logging_interceptor}")
  implementation("com.github.akarnokd:rxjava3-retrofit-adapter:${Versions.retrofit_rxjava3}")

  // coroutines
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutine_core}")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutine_android}")

  // kotlin
  implementation("org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}")
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}")

  // architecture
  implementation("com.airbnb.android:mvrx:${Versions.mavericks}")

  // test
  testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines_test}")
  testImplementation("org.mockito:mockito-core:${Versions.mockito_core}")

  // atsl
  testImplementation("androidx.test.ext:junit:${Versions.atsl_junit}")
  testImplementation("androidx.test:runner:${Versions.atsl_runner}")
  testImplementation("androidx.test:rules:${Versions.atsl_rules}")
  testImplementation("androidx.test.espresso:espresso-core:${Versions.atsl_espresso}")
  testImplementation("androidx.test.espresso:espresso-contrib:${Versions.atsl_espresso}")
  testImplementation("androidx.test.espresso:espresso-intents:${Versions.atsl_espresso}")
  testImplementation("androidx.arch.core:core-testing:${Versions.atsl_core_testing}")
  testImplementation("androidx.work:work-testing:${Versions.atsl_work_testing}")
}
