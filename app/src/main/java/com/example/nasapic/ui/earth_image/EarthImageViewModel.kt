package com.example.nasapic.ui.earth_image

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nasapic.BuildConfig
import com.example.nasapic.model.json_model.earth_image.EarthImageDTO
import com.example.nasapic.model.json_model.earth_image.EarthImageData
import com.example.nasapic.model.loader.PictureRepo

class EarthImageViewModel : ViewModel() {
    private val liveDataToObserve: MutableLiveData<EarthImageData> = MutableLiveData()

    fun getData(lon: String, lat: String, date: String, dim: String): LiveData<EarthImageData> {
        getImageFromSource(lon, lat, date, dim)
        return liveDataToObserve
    }

    private fun getImageFromSource(lon: String, lat: String, date: String, dim: String) {
        Thread {
            Thread.sleep(1000)
            liveDataToObserve.postValue(getEarthImage(lon, lat, date, dim))
        }.start()
    }

    private fun getEarthImage(lon: String, lat: String, date: String, dim: String): EarthImageData {
        val earthImageDTO: EarthImageDTO? =
            PictureRepo.api.getImageEarth(BuildConfig.API_KEY, lon, lat, date, dim).execute().body()
        var earthImageData: EarthImageData = EarthImageData()

        earthImageDTO?.let {
            earthImageData = EarthImageData(it.url)
        }

        return earthImageData
    }
}