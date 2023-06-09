package com.kraker.pokemon.data.components.context

import android.content.Context

actual class ContextWrapper(private val context: Context) {
    fun get() = context
}
