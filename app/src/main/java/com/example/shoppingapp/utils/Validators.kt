package com.example.shoppingapp.utils

object Validators {
    fun required(errorMessage: String = "Wymagane pole"): Pair<(String) -> Boolean, String> {
        return Pair({ text -> text.isNotEmpty() }, errorMessage)
    }

    fun regex(pattern: String, errorMessage: String): Pair<(String) -> Boolean, String> {
        val regex = Regex(pattern)
        return Pair({ text -> regex.matches(text) }, errorMessage)
    }

    fun isDouble(errorMessage: String = "Wartość musi być liczbą zmiennoprzecinkową"): Pair<(String) -> Boolean, String> {
        return regex("^(-?\\d+(\\.\\d+)?)?\$", errorMessage)
    }
}