object Versions {
    const val kotlin = "1.8.20"
    const val gradle = "7.4.2"

    const val koin = "3.4.0"

    const val ktor = "2.2.4"
    const val napier = "2.6.1"

    const val serialization = "1.5.0"

    const val coroutines = "1.6.4"

    const val composeMultiplatform = "1.4.0"
    const val resources = "0.23.0"

    const val compose = "1.4.2"
    const val activityCompose = "1.7.2"
    const val composeNavigation = "2.5.0"
    const val composeCoil = "2.1.0"
    const val composeLottie = "6.0.1"
    const val composeAccompanist = "0.23.1"
    const val composePaging = "1.0.0-alpha17"

    const val appCompat = "1.6.1"
    const val splashScreen = "1.0.0"
    const val paging = "2.1.2"

    const val ktlint = "11.3.2"

    const val precompose = "1.4.2"
    const val precomposeViewmodel = "1.4.2"
    const val imageLoader = "1.5.1"
}

object Plugins {
    const val serializationVersion = "1.5.0"
    const val kotlinSymbolProcesVersion = "${Versions.kotlin}-1.0.9"
    const val nativeCoroutinesVersion = "1.0.0-ALPHA-5"
}

object Deps {

    object Common {

        object Koin {
            const val core = "io.insert-koin:koin-core:${Versions.koin}"
            const val test = "io.insert-koin:koin-test:${Versions.koin}"
        }

        object Ktor {
            const val core = "io.ktor:ktor-client-core:${Versions.ktor}"
            const val neogotiation = "io.ktor:ktor-client-content-negotiation:${Versions.ktor}"
            const val serialization = "io.ktor:ktor-client-serialization:${Versions.ktor}"
            const val json = "io.ktor:ktor-serialization-kotlinx-json:${Versions.ktor}"
            const val logging = "io.ktor:ktor-client-logging:${Versions.ktor}"
            const val napier = "io.github.aakira:napier:${Versions.napier}"
        }

        object Kotlinx {
            const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
            const val serializationJson = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.serialization}"
        }
    }

    object Android {

        object Koin {
            const val compose = "io.insert-koin:koin-androidx-compose:${Versions.koin}"
        }

        object Ktor {
            const val client = "io.ktor:ktor-client-android:${Versions.ktor}"
            const val jvm = "io.ktor:ktor-client-core-jvm:${Versions.ktor}"
        }

        object Androidx {
            const val appCompat = "androidx.appcompat:appcompat:1.6.1"
            const val splashScreen = "androidx.core:core-splashscreen:${Versions.splashScreen}"
            const val paging = "androidx.paging:paging-runtime:${Versions.paging}"
        }

        object Compose {
            const val ui = "androidx.compose.ui:ui:${Versions.compose}"
            const val material = "androidx.compose.material:material:${Versions.compose}"
            const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
            const val uiPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
            const val foudation = "androidx.compose.foundation:foundation:${Versions.compose}"
            const val navigation = "androidx.navigation:navigation-compose:${Versions.composeNavigation}"

            const val activity = "androidx.activity:activity-compose:${Versions.activityCompose}"

            const val coil = "io.coil-kt:coil-compose:${Versions.composeCoil}"
            const val lottie = "com.airbnb.android:lottie-compose:${Versions.composeLottie}"
            const val accompanistSystemUi = "com.google.accompanist:accompanist-systemuicontroller:${Versions.composeAccompanist}"

            const val liveData = "androidx.compose.runtime:runtime-livedata:${Versions.compose}"
            const val composePaging = "androidx.paging:paging-compose:${Versions.composePaging}"
        }
    }

    object ios {

        object Ktor {
            const val client = "io.ktor:ktor-client-ios:${Versions.ktor}"
        }
    }
}