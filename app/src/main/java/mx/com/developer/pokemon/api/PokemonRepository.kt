package mx.com.developer.pokemon.api

import android.content.Context
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// We might need to change the endpoint to https later.
private const val BASE_URL = "https://pokeapi.co/api/v2/"
private const val CACHE_SIZE: Long = 10 * 1024 * 1024; // 10 MB


/**
 * Turns on/off client offline caching. By default is off.
 */
private const val IS_CACHE_CONTROL_ENABLED = false

/**
 * Class that holds the endpoints to communicate app with server.
 * Do not create an instance of this class. Instead, use dependency injection
 * in the following way for Activities, Fragments and Service
 *
 * class Activity, Fragment or Service: KoinComponent {
 *
 * val repository by inject<SonaRepository>()
 * .....
 * }
 *
 * For ViewModels, use BaseAndroidViewModel or BaseViewModel. No need to import KoinComponent.
 *
 * @param context androidApplicationContext
 */
class PokemonRepository (context: Context) {

    private val api: ApiEndpoints

    init {

        val logging = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }


        val client = OkHttpClient.Builder().apply {

            addInterceptor { chain ->
                val request = chain.request().newBuilder()
                val originalHttpUrl = chain.request().url()
                val url = originalHttpUrl.newBuilder().addQueryParameter("api_key", "2f72eea10d2663d258c92fe5cf505a55").build()
                request.url(url)
                val response = chain.proceed(request.build())
                return@addInterceptor response
            }

            addInterceptor(logging)
            if (IS_CACHE_CONTROL_ENABLED) {
                cache(Cache(context.cacheDir, CACHE_SIZE))
                addNetworkInterceptor(CacheInterceptor())
            }
        }.build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(ApiEndpoints::class.java)


    }

    fun getAPI() = api


}