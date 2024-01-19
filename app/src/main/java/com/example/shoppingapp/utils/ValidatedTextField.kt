package com.example.shoppingapp.utils

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ValidatedTextField(
    value: String,
    onValueChange: (String, Boolean) -> Unit,
    label: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    validators: List<Pair<(String) -> Boolean, String>> = listOf()
) {
    var errorMessage by remember { mutableStateOf("") }

    fun validate(text: String): Boolean {
        if(validators.isNullOrEmpty()) return true

        validators.forEach { (rule, errorMsg) ->
            if (!rule(text)) {
                errorMessage = errorMsg
                return false
            }
        }
        errorMessage = ""
        return true
    }

    TextField(
        value = value,
        onValueChange = { text ->
            val isValid: Boolean = validate(text)
            onValueChange(text, isValid)
        },
        label = label,
        keyboardOptions = keyboardOptions,
        isError = errorMessage.isNotEmpty(),
        supportingText = {
            if(errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error
                )
            }
        },
        trailingIcon = {
            if(errorMessage.isNotEmpty()) {
                Icon(
                    imageVector = Icons.Filled.Warning,
                    contentDescription = "Niepoprawna wartość"
                )
            }
        }
    )
}