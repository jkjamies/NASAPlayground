plugins {
    val serialization = libs.plugins.serialization.get()
    id(libs.plugins.android.library.get().pluginId)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.ksp)
    kotlin(serialization.pluginId) version serialization.version.requiredVersion
    alias(libs.plugins.sqldelight)
    alias(libs.plugins.jetbrains.dokka)
}

android {
    namespace = "com.jkjamies.nasa.neos"
    compileSdk = 34

    defaultConfig {
        minSdk = 23

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
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
}

ksp {
    arg("KOIN_CONFIG_CHECK", "true")
}

sqldelight {
    databases {
        create("NeosDatabase") {
            packageName.set("com.jkjamies.nasa.neos")
        }
    }
}

tasks.dokkaHtml {
    dokkaSourceSets.configureEach {
        includeNonPublic = true
    }
}

android.testOptions {
    unitTests.all {
        it.useJUnitPlatform()
    }
}

dependencies {

    // Modules
    implementation(project(":core"))

    // KMP-Friendly
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.okhttp)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.json)
    implementation(libs.kotlinx.serialization)
    // Koin
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
    // Koin Annotations
    implementation(platform(libs.koin.annotations.bom))
    implementation(libs.koin.annotations)
    implementation(libs.koin.ksp.compiler)
    ksp(platform(libs.koin.annotations.bom))
    ksp(libs.koin.ksp.compiler)
    // Kermit
    implementation(libs.kermit)
    implementation(libs.kermit.koin)

    // Android
    // SQLDelight
    implementation(libs.android.sqldelight.driver)
    implementation(libs.koin.android)

    // Test
    testImplementation(libs.junit.kotest)
    testImplementation(libs.ktor.client.mock)
    testImplementation(libs.mockk)
    testImplementation(libs.android.sqldelight.test.driver)
}
