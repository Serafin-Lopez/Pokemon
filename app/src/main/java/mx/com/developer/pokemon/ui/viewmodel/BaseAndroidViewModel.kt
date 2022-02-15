package mx.com.developer.pokemon.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import org.koin.core.KoinComponent

/**
 * Base class for ViewModels where Android Application is required.
 * It also has the implementation for Dependency injection, so no need to import it every time.
 * If you don't need androidApplication for your ViewModel use
 * @see BaseViewModel
 */
abstract class BaseAndroidViewModel(application: Application) : AndroidViewModel(application), KoinComponent