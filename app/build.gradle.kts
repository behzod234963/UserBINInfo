plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
    alias(libs.plugins.daggerHiltApp)
}

android {
    namespace = "com.mr.anonym.userbininfo"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.mr.anonym.userbininfo"
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

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //    Modules
    implementation(project(":data"))
    implementation(project(":domain"))

    //    Navigation compose
    implementation(libs.androidx.navigation.compose)

    //    Retrofit
    implementation(libs.gsonConverter)
    implementation(libs.retrofit2)

    //    Dagger Hilt
    implementation(libs.daggerHilt)
    implementation(libs.daggerHilt.navigation.compose)
    kapt(libs.daggerHiltKaptCompiler)

    //    ViewModel and lifecycle dependency's
    implementation(libs.viewModel)
    implementation(libs.viewModelUtilities)
    implementation(libs.liveData)
    implementation(libs.lifecycleUtilities)
    implementation(libs.viewModelSaveState)

    //    Coroutine
    implementation(libs.coroutine)

    //    Room SQLite
    implementation(libs.roomSQLite)
    implementation(libs.roomWithCoroutines)
    implementation(libs.roomPaging3)
    kapt(libs.kaptCompiler)

    //    LiveData extensions
    implementation(libs.liveDataExtensions)

    //    OkHttp
    implementation(libs.okHttp)
    implementation(platform(libs.okHttpBom))
    implementation(libs.okHttpLoggingInterceptor)
}
kapt {
    correctErrorTypes = true
}