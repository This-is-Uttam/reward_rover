plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("org.jetbrains.kotlin.android")

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

    buildFeatures {
        viewBinding = true
    }


}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment:2.7.7")
    implementation("androidx.navigation:navigation-ui:2.7.7")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation("com.google.firebase:firebase-auth:23.0.0")
    implementation("androidx.core:core-ktx:1.13.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

//    swipe refresh listener
    implementation ("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
//    Picasso
    implementation ("com.squareup.picasso:picasso:2.8")
//    volley
    implementation ("com.android.volley:volley:1.2.1")
//    location
    implementation ("com.google.android.gms:play-services-location:21.3.0")
//    google sign in
    implementation ("com.google.android.gms:play-services-auth:21.2.0")
    //  Firebase
    implementation(platform("com.google.firebase:firebase-bom:33.1.0"))

    implementation ("com.google.firebase:firebase-crashlytics")
    implementation ("com.google.firebase:firebase-analytics")
    implementation ("com.google.firebase:firebase-auth-ktx")
    implementation ("com.google.firebase:firebase-messaging")

//    CircularImage
    implementation ("de.hdodenhof:circleimageview:3.1.0")

    implementation ("com.github.guy-4444:StepLineIndicator:1.03.01")
    implementation ("androidx.browser:browser:1.5.0")

}