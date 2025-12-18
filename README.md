# Economiza Alagoas

App Android para comparação de preços e economia em Alagoas.

## Tecnologias

- Kotlin
- Jetpack Compose
- Room Database
- Supabase
- Retrofit
- OpenStreetMap

## Requisitos

- Android Studio
- JDK 11
- Android SDK 24+

## Configuração

1. Clone o repositório
2. Configure as credenciais no arquivo `local.properties`:
   ```
   supabase.url=sua-url
   supabase.key=sua-chave
   api.governo.url=sua-url
   api.governo.key=sua-chave
   ```
3. Sincronize o projeto com Gradle
4. Execute o app

## Build

```bash
./gradlew assembleDebug
```

## Licença

Projeto privado

