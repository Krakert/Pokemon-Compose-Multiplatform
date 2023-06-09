package com.kraker.pokemon

import android.os.Bundle
import android.graphics.Color
import androidx.core.view.WindowCompat
import com.kraker.pokemon.data.components.context.ContextWrapper
import com.kraker.pokemon.di.initKoin
import moe.tlaster.precompose.lifecycle.PreComposeActivity
import moe.tlaster.precompose.lifecycle.setContent
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

class MainActivity : PreComposeActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = Color.TRANSPARENT

        initKoin{
            modules(
                module {
                    androidContext(this@MainActivity)
                    factory { ContextWrapper(androidContext()) }
                }
            )
        }

        setContent {
            MainView()
        }
    }
}