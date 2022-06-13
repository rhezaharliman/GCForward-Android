package com.rheza.gcforward.helper.api

import com.rheza.gcforward.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

/**
 * RetrofitInterceptor
 * To do the request to Github API, header consists of Accept and Authorization element is necessary
 * Since there will be several request using the same header,
 * use this interceptor class to prevent repetition.
 */
class RetrofitInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request =
            chain.request()
                .newBuilder()
                .addHeader("Accept", ACCEPT)
                .addHeader("Authorization", TOKEN)
                .build()
        return chain.proceed(request)
    }

    // define header values
    companion object {
        const val ACCEPT = "application/vnd.github.v3+json"
        // to make the token at key.properties file is readable, build is necessary
        // MEMO: "const" cannot be used since appending string is not compile time constant that
        // is a must for "const".
        // To solve this, remove the "const" keyword and put JvmField annotation
        // https://stackoverflow.com/a/46482788
        @JvmField
        val TOKEN = "token " + BuildConfig.API_GITHUB_TOKEN
    }
}