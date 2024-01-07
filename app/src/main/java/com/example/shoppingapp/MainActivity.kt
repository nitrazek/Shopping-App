package com.example.shoppingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shoppingapp.ui.theme.ShoppingAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShoppingAppTheme {
                ShoppingApp()
            }
        }
    }
}

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ShoppingApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    val navItems = listOf(
        BottomNavigationItem(
            title = "Listy zakupów",
            selectedIcon = Icons.Filled.ShoppingCart,
            unselectedIcon = Icons.Outlined.ShoppingCart
        ),
        BottomNavigationItem(
            title = "Skanowanie produktu",
            selectedIcon = Icons.Filled.Search,
            unselectedIcon = Icons.Outlined.Search
        ),
        BottomNavigationItem(
            title = "Ulubione sklepy",
            selectedIcon = Icons.Filled.Favorite,
            unselectedIcon = Icons.Outlined.Favorite
        )
    )

    var selectedItemIndex = rememberSaveable { mutableIntStateOf(0) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            bottomBar = {
                NavigationBar {
                    navItems.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = selectedItemIndex.intValue == index,
                            onClick = {
                                selectedItemIndex.intValue = index
                                navController.navigate(item.title)
                            },
                            alwaysShowLabel = true,
                            icon = {
                                Icon(
                                    imageVector = if(selectedItemIndex.intValue == index) {
                                        item.selectedIcon
                                    } else item.unselectedIcon,
                                    contentDescription = item.title,
                                )
                            }
                        )
                    }
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = "Listy zakupów",
                modifier = Modifier.padding(innerPadding)
            ) {
                composable("Listy zakupów") { ShoppingLists(modifier) }
                composable("Skanowanie produktu") { Scanner(modifier) }
                composable("Ulubione sklepy") { FavouriteShops(modifier) }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShoppingAppPreview() {
    ShoppingAppTheme {
        ShoppingApp()
    }
}