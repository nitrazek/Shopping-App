package com.example.shoppingapp.ui.screens.addShop

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddShopScreen(
    navController: NavController,
    viewModel: AddShopViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            if(uiState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                )
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
                        label = { Text(text = "Nazwa") },
                        validators = listOf(
                            Validators.required()
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    ValidatedTextField(
                        value = uiState.latitudeText,
                        onValueChange = { latitude, isValid -> viewModel.setLatitude(latitude, isValid) },
                        label = { Text(text = "Szerokość geograficzna") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        validators = listOf(
                            Validators.isDouble()
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    ValidatedTextField(
                        value = uiState.longitudeText,
                        onValueChange = { longitude, isValid -> viewModel.setLongitude(longitude, isValid) },
                        label = { Text(text = "Długość geograficzna") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        validators = listOf(
                            Validators.isDouble()
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    ValidatedTextField(
                        value = uiState.cityText,
                        onValueChange = { city, isValid -> viewModel.setCity(city, isValid) },
                        label = { Text(text = "Miasto") }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    ValidatedTextField(
                        value = uiState.streetText,
                        onValueChange = { street, isValid -> viewModel.setStreet(street, isValid) },
                        label = { Text(text = "Ulica") }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    ValidatedTextField(
                        value = uiState.postalCodeText,
                        onValueChange = { postalCode, isValid -> viewModel.setPostalCode(postalCode, isValid) },
                        label = { Text(text = "Kod pocztowy") }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = {
                            viewModel.addShop()
                            scope.launch {
                                snackbarHostState.showSnackbar("Dodano sklep")
                            }
                            navController.navigate(context.getString(R.string.route_favourite_shops))
                        }
                    ) {
                        Text(text = "OK")
                    }
                }
            }
        }
    }
}