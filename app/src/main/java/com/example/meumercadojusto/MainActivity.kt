package com.example.meumercadojusto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.meumercadojusto.api.RetrofitClient
import com.example.meumercadojusto.db.DatabaseHelper
import com.example.meumercadojusto.model.Mercado
import com.example.meumercadojusto.ui.theme.MeuMercadoJustoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MeuMercadoJustoTheme {
                HomeScreen()
            }
        }
    }
}

@Composable
fun HomeScreen() {
    val context = LocalContext.current
    
    // Variáveis que guardam informações da tela
    var melhorMercado by remember { mutableStateOf<Mercado?>(null) }
    var carregando by remember { mutableStateOf(false) }
    var recarregar by remember { mutableStateOf(0) }
    
    // Função que busca dados da API e salva no banco
    suspend fun carregarDados() {
        carregando = true
        
        try {
            // Buscar produtos e mercados da API
            val produtos = RetrofitClient.mercadoApi.getProdutos()
            val mercadosApi = RetrofitClient.mercadoApi.getMercados()
            
            // Calcular o total de cada mercado
            val mercadosComTotal = mutableListOf<Mercado>()
            for (mercado in mercadosApi) {
                var total = 0.0
                for (produto in produtos) {
                    if (produto.mercadoId == mercado.id) {
                        total = total + produto.preco
                    }
                }
                mercadosComTotal.add(
                    Mercado(
                        id = mercado.id,
                        nome = mercado.nome,
                        endereco = mercado.endereco,
                        total = total
                    )
                )
            }
            
            // Salvar no banco de dados
            val db = DatabaseHelper.getInstance(context)
            db.mercadoDao().deleteAll()
            db.mercadoDao().insertAll(mercadosComTotal)
            
            // Buscar o mercado com menor total
            melhorMercado = db.mercadoDao().findMelhor()
            
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            carregando = false
        }
    }
    
    // Executar quando a tela aparece ou quando clica no botão
    LaunchedEffect(recarregar) {
        carregarDados()
    }
    
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(36.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Meu Mercado Justo",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(24.dp))
            
            Text(
                text = "A economia na palma das suas mãos",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            Spacer(Modifier.height(24.dp))
            
            Button(
                onClick = { recarregar++ },
                enabled = !carregando
            ) {
                if (carregando) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(16.dp),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    Spacer(Modifier.width(8.dp))
                }
                Text(if (carregando) "Carregando..." else "Carregar Cestas")
            }
            
            Spacer(Modifier.height(24.dp))
            
            // Mostrar melhor mercado se existir
            melhorMercado?.let { mercado ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF209822))
                ) {
                    Column(Modifier.padding(16.dp)) {
                        Text(
                            text = "Melhor custo benefício",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Spacer(Modifier.height(12.dp))
                        
                        Text(
                            text = mercado.nome,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = mercado.endereco,
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White.copy(alpha = 0.8f)
                        )
                        Spacer(Modifier.height(12.dp))
                        Text(
                            text = "Total: R$ %.2f".format(mercado.total),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}
