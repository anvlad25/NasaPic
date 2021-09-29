package com.example.nasapic.model.json_model.pic_of_the_day

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PictureData(
    val copyright: String = "Null",
    val date: String = "1900-01-01",
    val explanation: String = "Null",
    //val hdurl: String = "https://apod.nasa.gov/apod/image",
    val media_type: String = "Null",
    val service_version: String = "Null",
    val title: String = "Null",
    val url: String = "https://apod.nasa.gov/apod/image"
): Parcelable
