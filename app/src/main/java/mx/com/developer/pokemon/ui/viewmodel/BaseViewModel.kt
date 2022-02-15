package mx.com.developer.pokemon.ui.viewmodel

import androidx.lifecycle.ViewModel
import org.koin.core.KoinComponent

/**
 * Base class for ViewModels where.
 * It also has the implementation for Dependency injection, so no need to import it every time.
 * If you need androidApplication for your ViewModel use
 * @see BaseAndroidViewModel
 */
abstract class BaseViewModel : ViewModel(), KoinComponent