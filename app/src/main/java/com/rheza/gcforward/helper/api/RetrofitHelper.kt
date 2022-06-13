package com.rheza.gcforward.helper.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Define Retrofit for requesting to GitHub endpoint
 */
object RetrofitHelper {
    private const val BASE_URL = "https://api.github.com/"

    // implement the interceptor for the retrofit builder
    private val interceptorClient =
        OkHttpClient.Builder().apply { addInterceptor(RetrofitInterceptor()) }.build()

    private val retrofit: Retrofit by lazy {
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .client(interceptorClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    val githubApiHelper: GithubApiHelper by lazy {
        retrofit.create(GithubApiHelper::class.java)
    }
}
