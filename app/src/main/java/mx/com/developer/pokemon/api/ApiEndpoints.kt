package mx.com.developer.pokemon.api


import mx.com.developer.pokemon.api.models.PokemonListModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * IMPORTANT: Adding this header to a particular request will make
 * Retrofit to cache the response in the local cache directory.
 * This is only used for requests that we want to keep in local cache for a short period of time.
 * DO NOT USE IT if the request sends sensible data to the server or need to be up-to-date with the server.
 * Discuss first with the team before adding this header to a particular request.
 * @see CacheInterceptor
 */
private const val CACHE_CONTROL_POLICY_ACTIVE = "Cache-Control: public"

/**
 * Primary class that holds all the endpoints from Sona Repository
 */
interface ApiEndpoints {

    @GET("pokemon")
    fun getListPokemon(): Call<PokemonListModel>


}