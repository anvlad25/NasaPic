package com.example.nasapic.model.json_model.earth_image

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EarthImageData(
    val url: String = "https://earthengine.googleapis.com/v1alpha/projects/earthengine-legacy/thumbnails",
    val id: String = "-1"
): Parcelable
