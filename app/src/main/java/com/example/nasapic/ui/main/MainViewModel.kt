package com.example.nasapic.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nasapic.BuildConfig
import com.example.nasapic.model.json_model.pic_of_the_day.PictureData
import com.example.nasapic.model.json_model.pic_of_the_day.PictureDayDTO
import com.example.nasapic.model.loader.PictureRepo

class MainViewModel : ViewModel() {
    private val liveDataToObserve: MutableLiveData<PictureData> = MutableLiveData()

    fun getData(): LiveData<PictureData> {
        getPictureFromSource()
        return liveDataToObserve
    }

    private fun getPictureFromSource() {
        Thread {
            Thread.sleep(1000)
            liveDataToObserve.postValue(getPictureOfTheDay())
        }.start()
    }

    private fun getPictureOfTheDay(): PictureData {
        val pictureDayDTO: PictureDayDTO? =
            PictureRepo.api.getPictureOfTheDay(BuildConfig.API_KEY).execute().body()
        var pictureData: PictureData = PictureData()

        pictureDayDTO?.let {
            pictureData = PictureData(
                //it.copyright,
                it.date,
                it.explanation,
                //it.hdurl,
                it.media_type,
                it.service_version,
                it.title,
                it.url
            )
        }

        return pictureData
    }
}