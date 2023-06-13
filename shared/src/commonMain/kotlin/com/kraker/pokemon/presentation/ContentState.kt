package com.kraker.pokemon.presentation

sealed interface ContentState<out T>
class OnDisplay<out T>(val display: T) : ContentState<T>
object OnLoading : ContentState<Nothing>
class OnError(val errorDisplay: Throwable) : ContentState<Nothing>