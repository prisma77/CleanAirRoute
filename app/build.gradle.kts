plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.prisma77.cleanairroute"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.prisma77.cleanairroute"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        val naverClientId = project.findProperty("NAVER_CLIENT_ID") as? String ?: ""
        val seoulApiKey = project.findProperty("SEOUL_API_KEY") as? String ?: ""

        manifestPlaceholders["NAVER_CLIENT_ID"] = naverClientId
        manifestPlaceholders["SEOUL_API_KEY"] = seoulApiKey
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.activity:activity-compose:1.8.1")
    implementation("androidx.compose.ui:ui:1.5.4")
    implementation("androidx.compose.material3:material3:1.1.2")
    implementation("com.naver.maps:map-sdk:3.21.0")
    implementation("com.google.android.gms:play-services-location:21.0.1")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.google.code.gson:gson:2.10.1")
}
