package com.example.shoppingapp.ui.screens.scanner

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shoppingapp.utils.ApiService
import com.journeyapps.barcodescanner.ScanOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Preview(showBackground = true)
@Composable
fun ScannerScreen() {
    val context = LocalContext.current
    val scannedResult = remember { mutableStateOf<String>("")}
    val scannerLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val resultString = result.data?.getStringExtra("SCAN_RESULT")
            if(resultString != null) {
                val apiService = ApiService()
                apiService.getProduct(resultString, object: Callback<Map<String, Map<String, String>>> {
                    override fun onResponse(
                        call: Call<Map<String, Map<String, String>>>,
                        response: Response<Map<String, Map<String, String>>>
                    ) {
                        Log.d("API", response.toString())
                        Log.d("API", response.message())
                        Log.d("API", response.body().toString())
                        if (response.isSuccessful) {
                            val product = response.body()?.get("product")
                            val productName = product?.get("product_name")
                            if(productName != null) {
                                Toast.makeText(context, productName, Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(context, "Nieznany produkt", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            Toast.makeText(context, "Błąd przy pobieraniu produktu", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(
                        call: Call<Map<String, Map<String, String>>>,
                        t: Throwable
                    ) {
                        t.printStackTrace()
                    }

                })
            }
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Skanuj produkt", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    val options = ScanOptions()
                    options.setBeepEnabled(true)
                    options.setOrientationLocked(true)
                    val intent: Intent = options.createScanIntent(context)
                    scannerLauncher.launch(intent)
                }
            ) {
                Text("Skanuj")
            }
            Spacer(modifier = Modifier.height(50.dp))
            Text(text = scannedResult.value)
        }
    }
}