package com.example.meumercadojusto.ui.state

import com.example.meumercadojusto.model.Mercado

data class HomeScreenUiState(
    val melhorMercado: Mercado? = null,
    val todosMercados: List<Mercado> = emptyList(),
    val estatisticas: Estatisticas? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val onReloadClick: () -> Unit = {}
)

data class Estatisticas(
    val totalMercados: Int = 0,
    val economia: Double = 0.0
)