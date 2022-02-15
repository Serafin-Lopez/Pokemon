package mx.com.developer.pokemon.api

import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.Response

private const val CACHE_CONTROL = "Cache-Control"
// The maximum amount of time a resource is considered fresh in seconds.
private const val MAX_AGE_IN_SECONDS = 60 // <<<<< 1 minute for now (increment as needed)
private const val MAX_AGE = "max-age=$MAX_AGE_IN_SECONDS"


/**
 * Network Interceptor used to override the cache response when an specific
 * request requires to be cached for certain period of time.
 * This gives the capability to store responses that we might not want to ask for them all the time.
 * So for that we created this interceptor that basically asks if the given request contains "Cache-Control"
 * This header is added when we create the Call<> in the
 * @see ApiEndpoints to only the requests we want to cache for some period of time.
 * For more info about interceptors -> https://square.github.io/okhttp/interceptors/
 */
class CacheInterceptor(): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response = chain.request().run {
        if (isCacheControlPolicyEnabled(this.headers())) {
            chain.proceed(this).newBuilder().header(CACHE_CONTROL, MAX_AGE).build();
        } else {
            chain.proceed(chain.request())
        }
    }

    /**
     * Verify if the current request needs to be cached.
     * @return true if header Cache-Control was added, false otherwise
     */
    private fun isCacheControlPolicyEnabled(headers: Headers): Boolean {
        return headers.names().contains(CACHE_CONTROL)
    }
}