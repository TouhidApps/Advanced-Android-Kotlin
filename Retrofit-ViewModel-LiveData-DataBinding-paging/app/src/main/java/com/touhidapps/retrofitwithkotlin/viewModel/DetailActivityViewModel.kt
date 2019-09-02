package com.touhidapps.retrofitwithkotlin.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.touhidapps.retrofitwithkotlin.model.MovieModel
import com.touhidapps.retrofitwithkotlin.model.ServiceModel
import com.touhidapps.retrofitwithkotlin.repository.MovieRepository

class DetailActivityViewModel(application: Application) : AndroidViewModel(application) {

    private var movieRepository = MovieRepository(application)

    var mutableLiveData = MutableLiveData<ServiceModel>()

    fun getAllMovies(id: String) {
        movieRepository.loadData(id, action = {
            mutableLiveData.value = it
        })
    }



}



