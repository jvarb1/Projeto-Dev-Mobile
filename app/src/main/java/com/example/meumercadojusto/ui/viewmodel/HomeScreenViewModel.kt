package com.example.meumercadojusto.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meumercadojusto.data.repository.MercadoRepository
import com.example.meumercadojusto.ui.state.Estatisticas
import com.example.meumercadojusto.ui.state.HomeScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val mercadoRepository: MercadoRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        HomeScreenUiState(
            onReloadClick = { fetchMercados() }
        )
    )

    val uiState get() = _uiState.asStateFlow()

    init {
        fetchMercados()
    }

    private fun fetchMercados() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            try {
                val mercados = mercadoRepository.findAllMercados()
                val melhorMercado = mercadoRepository.findMelhorMercado()
                val estatisticas = calcularEstatisticas(mercados)

                _uiState.update {
                    it.copy(
                        melhorMercado = melhorMercado,
                        todosMercados = mercados.sortedBy { it.total },
                        estatisticas = estatisticas,
                        isLoading = false,
                        error = null
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = e.message
                    )
                }
            }
        }
    }

    private fun calcularEstatisticas(mercados: List<com.example.meumercadojusto.model.Mercado>): Estatisticas? {
        if (mercados.isEmpty()) return null

        val maisCaro = mercados.maxByOrNull { it.total }?.total ?: 0.0
        val maisBarato = mercados.minByOrNull { it.total }?.total ?: 0.0
        val economia = maisCaro - maisBarato

        return Estatisticas(
            totalMercados = mercados.size,
            economia = economia
        )
    }
}