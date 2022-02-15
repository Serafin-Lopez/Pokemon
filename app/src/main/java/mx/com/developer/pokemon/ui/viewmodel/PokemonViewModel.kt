package mx.com.developer.pokemon.ui.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import mx.com.developer.pokemon.api.PokemonRepository
import mx.com.developer.pokemon.api.models.PokemonListModel
import org.koin.core.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonViewModel(application: Application) : BaseAndroidViewModel(application) {

    private val repository by inject<PokemonRepository>()
    val liveData: MutableLiveData<List<PokemonListModel.Result>> = MutableLiveData()
    val error: MutableLiveData<String> = MutableLiveData()
    val status: MutableLiveData<Boolean> = MutableLiveData()
    val detailPokemon : MutableLiveData<PokemonListModel.Result> = MutableLiveData()

    init {
        liveData.value = listOf()
        error.value = null
        status.value = null
        detailPokemon.value = null
    }

    fun getListPokemon(){

        repository.getAPI().getListPokemon().enqueue(object: Callback<PokemonListModel> {

            override fun onResponse(call: Call<PokemonListModel>, response: Response<PokemonListModel>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        it.results.let { categories ->
                            liveData.postValue(categories)
                            status.value = true
                        }
                    }
                } else {
                    status.value = false
                }
            }

            override fun onFailure(call: Call<PokemonListModel>, t: Throwable) {
                error.postValue(t.message)
            }
        })
    }

}
