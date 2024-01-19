package com.example.shoppingapp.ui.screens.allShopingLists

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.shoppingapp.R
import com.example.shoppingapp.models.repositories.ProductWithQuantity
import com.example.shoppingapp.ui.theme.ShoppingAppTheme
import com.example.shoppingapp.utils.ValidatedTextField
import com.example.shoppingapp.utils.Validators
import com.example.shoppingapp.viewmodels.AllShoppingListsViewModel

@Composable
fun AllShoppingListsScreen(viewModel: AllShoppingListsViewModel = hiltViewModel()) {
    //val uiState by viewModel.uiState.collectAsStateWithLifecycle()


}



@Preview(showBackground = true)
@Composable
fun AllShoppingListScreenPreview() {
    AllShoppingListsScreen()
}


