package com.touhidapps.retrofitwithkotlin.service

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    private var retrofit: Retrofit? = null

    fun instance(): RetrofitInterface? {
        if (retrofit == null) {

            val gson = GsonBuilder().setLenient().create()

            val okHttpClient = OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build()

            retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(MyApiUrl.BASE_URL)
                .client(okHttpClient)
                .build()

        }
        return retrofit?.create(RetrofitInterface::class.java)
    }


}