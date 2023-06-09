package com.kraker.pokemon.di

import com.kraker.pokemon.di.modules.datamodule
import com.kraker.pokemon.di.modules.domainModule
import com.kraker.pokemon.di.modules.platformModule
//import com.kraker.pokemon.di.modules.platformModule
import com.kraker.pokemon.di.modules.presentationModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}) {
    startKoin {
        appDeclaration()
        modules(datamodule, domainModule, presentationModule, platformModule)
    }
}
