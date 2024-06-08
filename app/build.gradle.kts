plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.rewardrover"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.rewardrover"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
//
//    buildFeatures {
//        viewBinding = true
//    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment:2.7.7")
    implementation("androidx.navigation:navigation-ui:2.7.7")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")


//    Shimmer Recycler view
//    implementation ("com.github.sharish:ShimmerRecyclerView:v1.3") deprecated/repo not found
//    swipe refresh listener
    implementation ("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
}