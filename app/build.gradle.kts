plugins {
    id("com.android.application")
    
}
android {
    namespace = "net.oneui.settings.clone"
    compileSdk = 33
    
    defaultConfig {
        applicationId = "net.oneui.settings.clone"
        minSdk = 27
        targetSdk = 31
        versionCode = 6110
        versionName = "6.1 Stable 1.O"
        
        vectorDrawables { 
            useSupportLibrary = true
        }
    }
    
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        viewBinding = true
        
    }
    
}

dependencies {
    implementation("io.github.oneuiproject:design:1.2.3")
    implementation("io.github.oneuiproject.sesl:appcompat:1.3.0")
    implementation("io.github.oneuiproject.sesl:material:1.4.0")
    implementation("io.github.oneuiproject.sesl:recyclerview:1.3.0")
    implementation("io.github.oneuiproject.sesl:preference:1.1.0")
    implementation("io.github.oneuiproject.sesl:picker-color:1.0.1")
    implementation("io.github.oneuiproject.sesl:picker-basic:1.1.0")
    implementation("io.github.oneuiproject:icons:1.1.0")
}
