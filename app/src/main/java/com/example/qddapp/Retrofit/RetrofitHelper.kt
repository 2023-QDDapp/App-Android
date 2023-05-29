package com.example.qddapp.Retrofit

import android.content.Context
import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper(val context: Context) {

    var retrofitService: RetrofitService? = null

    fun getRetrofit(): RetrofitService {
        if (retrofitService == null) {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://test3.qastusoft.com.es")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okhttpClient())
                .build()

            retrofitService = retrofit.create(RetrofitService::class.java)
        }

        return retrofitService!!
    }

    private fun okhttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(HeaderInterceptor())
            .build()

        return client
    }

   inner class HeaderInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response = chain.run {
            val pref = context.getSharedPreferences("pref_filter", Context.MODE_PRIVATE)
            val token = pref.getString("token", "")
            proceed(
                request()
                    .newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()
            )
        }
   }
}