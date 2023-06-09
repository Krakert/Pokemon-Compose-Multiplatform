package com.kraker.pokemon

import com.kraker.pokemon.di.initKoin
import moe.tlaster.precompose.PreComposeApplication
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    initKoin()
    return PreComposeApplication(title = "") {
        App()
    }
}