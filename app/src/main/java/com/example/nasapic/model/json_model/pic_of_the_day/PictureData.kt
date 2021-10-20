package com.example.nasapic.model.json_model.pic_of_the_day

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PictureData(
    //val copyright: String = "pic",
    val date: String = "1900-01-01",
    val explanation: String = "pic",
    //val hdurl: String = "https://apod.nasa.gov/apod/image",
    val media_type: String = "empty",
    val service_version: String = "1",
    val title: String = "pic",
    val url: String = "https://apod.nasa.gov/apod/image"
): Parcelable
