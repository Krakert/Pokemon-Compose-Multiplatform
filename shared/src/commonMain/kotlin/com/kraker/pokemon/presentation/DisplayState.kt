package com.kraker.pokemon.presentation

sealed interface DisplayState<out T>
class OnDisplay<out T>(val display: T) : DisplayState<T>
object OnLoading : DisplayState<Nothing>
class OnError(val errorDisplay: Throwable) : DisplayState<Nothing>