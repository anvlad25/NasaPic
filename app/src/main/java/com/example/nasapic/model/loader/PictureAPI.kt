package com.example.nasapic.model.loader

import com.example.nasapic.model.json_model.pic_of_the_day.PictureDayDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PictureAPI {
    @GET("planetary/apod")
    fun getPictureOfTheDay(
        @Query("api_key") api_key: String
    ): Call<PictureDayDTO>
}