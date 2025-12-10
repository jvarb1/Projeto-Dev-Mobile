package com.example.meumercadojusto.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.meumercadojusto.model.Mercado

@Composable
fun CardMercado(
    mercado: Mercado,
    modifier: Modifier = Modifier
) {
    Surface(
        color = Color.White,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = mercado.nome,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = mercado.endereco,
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "Total: R$ %.2f".format(mercado.total),
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )
        }
    }
}