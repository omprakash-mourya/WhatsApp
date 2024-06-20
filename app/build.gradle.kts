plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
//    id("com.android.application")
    id("com.google.gms.google-services")
    id("kotlin-kapt")
    id("io.realm.kotlin")
//    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    //extra
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.opdroid.whatsapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.opdroid.whatsapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.firebase.auth.ktx)
    implementation(libs.androidx.lifecycle.runtime.compose.android)
    implementation(libs.firebase.storage.ktx)
//    implementation(libs.androidx.material3.android)
    val compose_version = "1.6.7"
    val hilt_version = "2.51.1"
    val coroutine_android = "1.6.4"
    val coroutine_core = "1.6.4"
    val lifecycle_version = "2.6.0-alpha01"
    val room = "2.6.1"

    //image cropper library
//    implementation(libs.android.image.cropper)
//    implementation("com.github.CanHub:Android-Image-Cropper:4.3.0")

    implementation("androidx.lifecycle:lifecycle-runtime-compose:$compose_version")
    implementation("androidx.compose.ui:ui:$compose_version")
    implementation("androidx.compose.material:material:$compose_version")
    implementation("androidx.compose.ui:ui-tooling-preview:$compose_version")

    //firebase
    implementation(platform("com.google.firebase:firebase-bom:33.1.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-database-ktx")
    implementation("com.google.firebase:firebase-firestore-ktx")
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.android.gms:play-services-auth")

    // Room
    implementation("androidx.room:room-runtime:$room")
    implementation("androidx.room:room-ktx:$room")
    kapt("androidx.room:room-compiler:$room")

    // Realm
    implementation("io.realm.kotlin:library-base:1.16.0")
    // If using Device Sync
    implementation("io.realm.kotlin:library-sync:1.16.0")

    //dagger hilt
    implementation("com.google.dagger:hilt-android:$hilt_version")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    kapt("com.google.dagger:hilt-compiler:$hilt_version")
    kapt("androidx.hilt:hilt-compiler:1.2.0")

    //hilt viewmodel
//    implementation("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")

    //ViewModels
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.5.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")

    implementation("io.coil-kt:coil-compose:2.1.0")

    //kotlin Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutine_android")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutine_core")

    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation("com.google.accompanist:accompanist-systemuicontroller:0.28.0")
}
