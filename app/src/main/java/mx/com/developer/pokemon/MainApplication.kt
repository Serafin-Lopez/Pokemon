package mx.com.developer.pokemon

import android.app.Application
import mx.com.developer.pokemon.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger


/**
 * Base class for maintaining global application state.
 */
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin()

    }

    /**
     * Initialize Koin Dependency Injection
     */
    private fun startKoin() {
        org.koin.core.context.startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            koin.loadModules(listOf(appModules))
            koin.createRootScope()
        }
    }

}