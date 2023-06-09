package com.kraker.pokemon.di.modules

import com.kraker.pokemon.data.components.storage.StorageRepository
import org.koin.dsl.module

actual val platformModule = module {
    single { StorageRepository() }
}
