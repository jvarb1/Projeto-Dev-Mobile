package com.example.meumercadojusto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.meumercadojusto.data.api.RetrofitClient
import com.example.meumercadojusto.data.db.DatabaseHelper
import com.example.meumercadojusto.data.repository.MercadoRepository
import com.example.meumercadojusto.ui.screens.HomeScreen
import com.example.meumercadojusto.ui.theme.MeuMercadoJustoTheme
import com.example.meumercadojusto.ui.viewmodel.HomeScreenViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MeuMercadoJustoTheme {
                val context = LocalContext.current
                val viewModel: HomeScreenViewModel = viewModel(
                    initializer = {
                        HomeScreenViewModel(
                            MercadoRepository(
                                DatabaseHelper.getInstance(context).mercadoDao(),
                                RetrofitClient.mercadoApi
                            )
                        )
                    }
                )
                HomeScreen(viewModel = viewModel)
            }
        }
    }
}
