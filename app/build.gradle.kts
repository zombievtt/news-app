plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

    id("com.google.dagger.hilt.android")

    alias(libs.plugins.ksp)

    id("org.jetbrains.kotlin.plugin.compose")

}

android {
    namespace = "com.example.newsapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.newsapp"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }
}



dependencies {

    // Hilt
    implementation("com.google.dagger:hilt-android:2.57")
    implementation(libs.androidx.junit.ktx)
    implementation(libs.androidx.room3.external.antlr)
    implementation(libs.androidx.ui)
    ksp("com.google.dagger:hilt-compiler:2.57")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    // Room
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    ksp("androidx.room:room-compiler:2.6.1")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    implementation(platform("androidx.compose:compose-bom:2025.01.00"))
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")

    implementation("io.coil-kt:coil-compose:2.6.0")

    implementation("androidx.navigation:navigation-compose:2.8.0")

    //Icon
    implementation("androidx.compose.material:material-icons-extended")

    //Test
    testImplementation("junit:junit:4.13.2")
    testImplementation(
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.1"
    )
    testImplementation(
        "app.cash.turbine:turbine:1.1.0"
    )
    testImplementation(
        "io.mockk:mockk:1.13.12"
    )

    //JUnit4
    testImplementation("junit:junit:4.13.2")
    testImplementation(
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.1"
    )
    testImplementation(
        "app.cash.turbine:turbine:1.1.0"
    )
    testImplementation(
        "io.mockk:mockk:1.13.12"
    )
}