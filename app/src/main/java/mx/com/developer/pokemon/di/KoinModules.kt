package mx.com.developer.pokemon.di


import mx.com.developer.pokemon.api.PokemonRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val appModules = module {
    // Single instance of PokemonRepository during the app lifecycle
    single { PokemonRepository(androidContext()) }
}