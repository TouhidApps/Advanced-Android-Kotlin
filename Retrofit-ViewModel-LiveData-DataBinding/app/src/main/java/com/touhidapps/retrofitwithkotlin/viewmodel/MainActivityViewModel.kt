package com.touhidapps.retrofitwithkotlin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.touhidapps.retrofitwithkotlin.repository.MovieRepository

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    var movieRepository = MovieRepository(application)

    fun getAllMovies(pageNo: Int) {
        movieRepository.loadData(pageNo)
    }

}



