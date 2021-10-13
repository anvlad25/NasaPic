package com.example.nasapic.model.json_model.pic_of_the_day

data class PictureDayDTO(
    val copyright: String,
    val date: String,
    val explanation: String,
    //val hdurl: String,
    val media_type: String,
    val service_version: String,
    val title: String,
    val url: String
)
