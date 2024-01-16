package com.example.shoppingapp.ui.screens.favouriteshops

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shoppingapp.models.Shop

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShopItem(shop: Shop) {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color = Color.LightGray,
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, Color.Black)
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Row {
                Text(
                    text = shop.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    modifier = Modifier.size(30.dp),
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "Szczegóły"
                )
                Spacer(modifier = Modifier.width(10.dp))
                Icon(
                    modifier = Modifier.size(30.dp),
                    imageVector = Icons.Filled.LocationOn,
                    contentDescription = "Pokaż na mapie"
                )
            }
            Row {
                Text(
                    text = shop.getAddressText(),
                    fontSize = 15.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShopItemPreview() {
    ShopItem(shop = Shop(
        name = "NAjLepSZE HOTdogI!!11!",
        city = "Białystok",
        street = "Szkolna 17",
        postalCode = "15-123"
    ))
}