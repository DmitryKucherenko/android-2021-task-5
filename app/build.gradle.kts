plugins {
    id ("com.android.application")
    id ("kotlin-android")
    id ("kotlin-kapt")
}

subprojects {
    apply (plugin = "org.jlleitschuh.gradle.ktlint")
    configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
        debug.set(false)
    }

}


android {
    compileSdkVersion(30)
    buildToolsVersion("29.0.3")

    buildFeatures.viewBinding = true

    defaultConfig {
        applicationId("com.fatalzero")
        minSdkVersion(26)
        targetSdkVersion(30)
        versionCode(1)
        versionName("1.0")

        testInstrumentationRunner = ("androidx.test.runner.AndroidJUnitRunner")
    }

    buildTypes {
        getByName("release") {
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

    tasks {
        withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
            kotlinOptions.jvmTarget = "1.8"
        }
    }

    dependencies {

        val kotlin_version = "1.5.10"
        val okHttp_version = "4.9.0"
        val retrofit_version = "2.9.0"
        val moshi_version = "1.12.0"
        val nav_version = "2.3.5"
        val pagingVersion = "3.0.0"

        implementation("org.jetbrains.kotlin:kotlin-stdlib:${kotlin_version}")
        implementation("androidx.navigation:navigation-fragment-ktx:${nav_version}")
        implementation("androidx.navigation:navigation-ui-ktx:${nav_version}")
        implementation("androidx.core:core-ktx:1.6.0")
        implementation("androidx.appcompat:appcompat:1.3.1")
        implementation("com.google.android.material:material:1.4.0")
        implementation("androidx.constraintlayout:constraintlayout:2.1.1")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0")
        implementation("com.squareup.retrofit2:retrofit:${retrofit_version}")
        implementation("com.squareup.okhttp3:logging-interceptor:${okHttp_version}")
        implementation("com.squareup.retrofit2:converter-moshi:${retrofit_version}")
        implementation("com.squareup.moshi:moshi:${moshi_version}")
        implementation("androidx.legacy:legacy-support-v4:1.0.0")
        implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.3.1")
        implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1")
        kapt("com.squareup.moshi:moshi-kotlin-codegen:${moshi_version}")
        implementation("com.github.bumptech.glide:glide:4.12.0")
        kapt("com.github.bumptech.glide:compiler:4.12.0")
        implementation("androidx.paging:paging-runtime-ktx:${pagingVersion}")
    }
}
