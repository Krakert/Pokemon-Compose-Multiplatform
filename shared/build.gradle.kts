plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    kotlin("plugin.serialization") version Plugins.serializationVersion
    id("com.android.library")
    id("org.jetbrains.compose") version Versions.composeMultiplatform
    id("dev.icerock.mobile.multiplatform-resources") version Versions.resources
}

kotlin {
    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        version = "1.0.0"
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
        }
        extraSpecAttributes["resources"] = "['src/commonMain/resources/**', 'src/iosMain/resources/**']"
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.animation)
                implementation(compose.material)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
                api(compose.materialIconsExtended)

                with(Deps.Common.Koin) {
                    api(core)
                }
                with(Deps.Common.Kotlinx) {
                    implementation(coroutines)
                    implementation(serializationJson)
                }

                with(Deps.Common.Ktor) {
                    implementation(core)
                    implementation(neogotiation)
                    implementation(serialization)
                    implementation(json)
                    implementation(logging)
                    implementation(napier)
                }

                api("io.github.qdsfdhvh:image-loader:${Versions.imageLoader}")

                api("moe.tlaster:precompose:${Versions.precompose}")
                api("moe.tlaster:precompose-viewmodel:${Versions.precomposeViewmodel}")

                api("dev.icerock.moko:resources:${Versions.resources}")
                api("dev.icerock.moko:resources-compose:${Versions.resources}")

                implementation("com.moriatsushi.insetsx:insetsx:0.1.0-alpha07")

            }
        }
        val androidMain by getting {
            dependencies {
                api("androidx.activity:activity-compose:${Versions.activityCompose}")
                api("androidx.appcompat:appcompat:${Versions.appCompat}")
                api("androidx.core:core-ktx:1.10.1")

                with(Deps.Android.Ktor) {
                    implementation(client)
                    implementation(jvm)
                }
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                with(Deps.ios.Ktor) {
                    implementation(client)
                }
            }
        }
    }
}

android {
    compileSdk = (findProperty("android.compileSdk") as String).toInt()
    namespace = "com.kraker.pokemon"

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        minSdk = (findProperty("android.minSdk") as String).toInt()
        targetSdk = (findProperty("android.targetSdk") as String).toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }
}

multiplatformResources {
    multiplatformResourcesPackage = "com.kraker.pokemon"
}