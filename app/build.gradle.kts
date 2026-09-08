plugins {
    id("com.android.application")
}

android {
    namespace = "com.oddscompare.app"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.oddscompare.app"
        minSdk = 24
        targetSdk = 36
        versionCode = 2
        versionName = "2.0"
    }

    signingConfigs {
        create("release") {
            storeFile = file(
                System.getenv("KEYSTORE_FILE")
                    ?: "oddscompare-upload-key.jks"
            )

            storePassword = System.getenv("KEYSTORE_PASSWORD")
            keyAlias = System.getenv("KEY_ALIAS")
            keyPassword = System.getenv("KEY_PASSWORD")
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false

            signingConfig = signingConfigs.getByName("release")

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation("androidx.webkit:webkit:1.17.0")
    implementation("androidx.activity:activity-ktx:1.12.2")
    implementation("androidx.core:core-ktx:1.18.0")
}
