package com.example.shoppingapp.viewmodels

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.ui.screens.scanner.ScannerScreenUiState
import com.journeyapps.barcodescanner.ScanOptions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScannerScreenViewModel @Inject constructor(
    private val application: Application
): AndroidViewModel(application) {
    private val _uiState = MutableStateFlow(ScannerScreenUiState())
    val uiState: StateFlow<ScannerScreenUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {

        }
    }
}