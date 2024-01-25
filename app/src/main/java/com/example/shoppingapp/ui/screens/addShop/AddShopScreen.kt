package com.example.shoppingapp.ui.screens.addShop

import android.Manifest
import android.content.Context
import android.graphics.Paint.Align
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.shoppingapp.R
import com.example.shoppingapp.utils.ValidatedTextField
import com.example.shoppingapp.utils.Validators
import com.example.shoppingapp.viewmodels.AddShopViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun AddShopScreen(
    navController: NavController,
    snackbarHostState: SnackbarHostState,
    viewModel: AddShopViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val scope = rememberCoroutineScope()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val locationPermissionState =
        rememberPermissionState(Manifest.permission.ACCESS_COARSE_LOCATION)

    val locationPermissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { permissions: Map<String, Boolean> ->
            if(permissions.values.all { it }) {
                viewModel.getLocation()
            } else {
                scope.launch {
                    snackbarHostState.showSnackbar("Brak dostępu do lokalizacji")
                }
            }
        }
    )

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        if (uiState.isLoading) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                )
                Text("Ładowanie...")
            }
        } else {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.route_add_shop),
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = Modifier.height(16.dp))
                ValidatedTextField(
                    value = uiState.nameText,
                    onValueChange = { name, isValid -> viewModel.setName(name, isValid) },
                    label = { Text(text = stringResource(id = R.string.shop_name_placeholder)) },
                    validators = listOf(
                        Validators.required()
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                ValidatedTextField(
                    value = uiState.latitudeText,
                    onValueChange = { latitude, isValid ->
                        viewModel.setLatitude(
                            latitude,
                            isValid
                        )
                    },
                    label = { Text(text = stringResource(id = R.string.shop_latitude_placeholder)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    validators = listOf(
                        Validators.isDouble()
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                ValidatedTextField(
                    value = uiState.longitudeText,
                    onValueChange = { longitude, isValid ->
                        viewModel.setLongitude(
                            longitude,
                            isValid
                        )
                    },
                    label = { Text(text = stringResource(id = R.string.shop_longitude_placeholder)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    validators = listOf(
                        Validators.isDouble()
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                ValidatedTextField(
                    value = uiState.cityText,
                    onValueChange = { city, isValid -> viewModel.setCity(city, isValid) },
                    label = { Text(text = stringResource(id = R.string.shop_city_placeholder)) }
                )
                Spacer(modifier = Modifier.height(8.dp))
                ValidatedTextField(
                    value = uiState.streetText,
                    onValueChange = { street, isValid -> viewModel.setStreet(street, isValid) },
                    label = { Text(text = stringResource(id = R.string.shop_street_placeholder)) }
                )
                Spacer(modifier = Modifier.height(8.dp))
                ValidatedTextField(
                    value = uiState.postalCodeText,
                    onValueChange = { postalCode, isValid ->
                        viewModel.setPostalCode(
                            postalCode,
                            isValid
                        )
                    },
                    label = { Text(text = stringResource(id = R.string.shop_postal_code_placeholder)) }
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    Button(
                        onClick = {
                            viewModel.addShop().fold(
                                ifLeft = {},
                                ifRight = {
                                    scope.launch {
                                        snackbarHostState.showSnackbar("Dodano sklep")
                                    }
                                    navController.navigate(context.getString(R.string.route_favourite_shops))
                                }
                            )
                        }
                    ) {
                        Text(text = "OK")
                    }
                    Button(
                        onClick = {
                            locationPermissionLauncher.launch(locationPermissions)
                        }
                    ) {
                        Text(text = "Lokalizacja")
                    }
                }
            }
        }
    }
}
