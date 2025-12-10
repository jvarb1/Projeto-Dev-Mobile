package com.example.meumercadojusto.data.repository

import com.example.meumercadojusto.data.api.RemoteMercadoApiDataSource
import com.example.meumercadojusto.data.db.LocalMercadoDataSource
import com.example.meumercadojusto.model.Mercado
import com.example.meumercadojusto.model.Produto      

class MercadoRepository(
    private val localDataSource: LocalMercadoDataSource,
    private val remoteDataSource: RemoteMercadoApiDataSource
){
    suspend fun findAllMercados(): List<Mercado>{
        var mercados = emptyList<Mercado>()
        try{
            val produtos = remoteDataSource.getProdutos()
            val mercadosApi = remoteDataSource.getMercados()
            mercados = calcularTotais(produtos, mercadosApi)

            localDataSource.upsertAll(mercados)
        } catch (e: Exception){
            mercados = localDataSource.findAll()
        }
        return mercados
    }
    suspend fun findMelhorMercado(): Mercado?{
        return localDataSource.findMelhor()
    }
    private fun calcularTotais(
        produtos: List<Produto>,
        mercados: List<Mercado>
    ): List<Mercado>{
        val mercadosComTotal = mutableListOf<Mercado>()
        for (mercado in mercados){
            var total = 0.0
            for (produto in produtos){
                if (produto.mercadoId == mercado.id){
                    total += produto.preco
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
        return mercadosComTotal
    }
}