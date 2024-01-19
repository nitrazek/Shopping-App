package com.example.shoppingapp.ui.screens.addShop

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.shoppingapp.R
import com.example.shoppingapp.utils.ValidatedTextField
import com.example.shoppingapp.utils.Validators
import com.example.shoppingapp.viewmodels.AddShopViewModel

@Composable
fun AddShopScreen(
    viewModel: AddShopViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
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
                onClick = { viewModel.addShop() }
            ) {
                Text(text = "OK")
            }
        }
    }
}