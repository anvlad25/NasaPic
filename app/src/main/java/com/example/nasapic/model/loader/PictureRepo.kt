package com.example.nasapic.model.loader

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PictureRepo {
    private const val BASE_URL: String = "https://api.nasa.gov/"

    val api: PictureAPI by lazy {
        val adapter = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(ApiUtils.getOkHTTPBuilderWithHeaders())
            .build()

        adapter.create(PictureAPI::class.java)
    }
}