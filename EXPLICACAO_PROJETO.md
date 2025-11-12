# 📱 Meu Mercado Justo

Aplicativo Android que compara preços de produtos entre diferentes mercados e mostra qual oferece o melhor custo-benefício.

## 🎯 Funcionalidade

O app busca produtos e mercados de uma API, calcula o total de cada mercado e exibe qual tem o menor preço total.

## 🛠️ Tecnologias Utilizadas

- **Kotlin** - Linguagem de programação
- **Jetpack Compose** - Interface do usuário
- **Retrofit** - Cliente HTTP para consumo de API
- **Room** - Banco de dados local
- **Coroutines** - Programação assíncrona

## 📋 Requisitos Atendidos

✅ **Tela funcional** - Consome dados da API e do banco de dados  
✅ **Banco de Dados** - Tabela `mercado` usando Room  
✅ **API** - Consome API fake com rotas `/produtos` e `/mercados`  
✅ **Coroutines** - Todas operações assíncronas usando `suspend fun`

## 🏗️ Estrutura do Projeto

```
app/src/main/java/com/example/meumercadojusto/
├── api/
│   ├── RetrofitClient.kt      # Configuração do cliente HTTP
│   └── MercadoApi.kt          # Interface com endpoints da API
├── db/
│   ├── DatabaseHelper.kt      # Configuração do banco Room
│   └── MercadoDao.kt          # Operações do banco de dados
├── model/
│   ├── Mercado.kt             # Modelo de dados (Entity)
│   └── Produto.kt             # Modelo de dados
└── MainActivity.kt            # Tela principal
```

## 🔌 API

- **URL Base:** `https://my-json-server.typicode.com/jvarb1/API-FAKE/`
- **Endpoints:**
  - `GET /produtos` - Lista de produtos
  - `GET /mercados` - Lista de mercados

## 💾 Banco de Dados

- **Tabela:** `mercado`
- **Campos:** `id`, `nome`, `endereco`, `total`
- **Operações:** Inserir, buscar, deletar, encontrar melhor custo-benefício

## 🚀 Como Funciona

1. Usuário clica em "Carregar Cestas"
2. App busca produtos e mercados da API
3. Calcula o total de cada mercado (soma dos preços)
4. Salva no banco de dados Room
5. Busca o mercado com menor total
6. Exibe na tela

## 📦 Dependências Principais

```kotlin
// Retrofit
implementation("com.squareup.retrofit2:retrofit:2.9.0")
implementation("com.squareup.retrofit2:converter-gson:2.9.0")

// Room
implementation("androidx.room:room-runtime:2.8.1")
ksp("androidx.room:room-compiler:2.8.1")
```

## 👨‍💻 Desenvolvido por

João Victor Araujo Rocha Brito - SI IFAL

---

**Projeto desenvolvido para aprendizado de integração entre API e Banco de Dados em Android.**
