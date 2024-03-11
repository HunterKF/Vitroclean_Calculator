import java.util.Properties

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.21")
        classpath("com.codingfeline.buildkonfig:buildkonfig-gradle-plugin:0.15.1")
    }
}

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    kotlin("plugin.serialization") version Deps.kotlinVersion
    id("com.google.devtools.ksp") version Deps.kspVersion
    id("com.codingfeline.buildkonfig") version "+"
}
repositories {
    mavenCentral()
}
kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            isStatic = false
            baseName = "shared"
        }
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Deps.ktorCore)
                implementation(Deps.ktorSerialization)
                implementation(Deps.ktorSerializationJson)
                implementation(Deps.kotlinDateTime)
                implementation(Deps.supabasePostgrest)

            }

        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(Deps.assertK)
                implementation(Deps.turbine)
                implementation(Deps.mockative)
                implementation(Deps.ktorTesting)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(Deps.ktorAndroid)
            }
        }
//        val androidUnitTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)

            dependencies {
                implementation(Deps.ktorIOS)
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }

}



dependencies {
    configurations
        .filter { it.name.startsWith("ksp") && it.name.contains("Test") }
        .forEach {
            add(it.name, "io.mockative:mockative-processor:2.0.1")
        }
}


android {
    namespace = "com.jaegerapps.trivitro_calculator"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
        targetSdk = 33
    }

}


buildkonfig {
    packageName = "com.jaegerapps.trivitro_calculator"

    defaultConfigs {
        val localProperties = Properties()
        val localPropertiesFile = rootProject.file("local.properties")
        if (localPropertiesFile.exists()) {
            localPropertiesFile.inputStream().use {
                localProperties.load(it)
            }
        }
        val apiKey = localProperties.getProperty("API_KEY") ?: "default_value"
        buildConfigField(com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING,
            "API_KEY", apiKey)
    }
}