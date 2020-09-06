package com.touhidapps.retrofitwithcoroutine.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.touhidapps.retrofitwithcoroutine.model.FlowerModel
import com.touhidapps.retrofitwithcoroutine.repository.FlowersRepository

class FlowersViewModel(application: Application): AndroidViewModel(application) {

    private var flowersRepository = FlowersRepository(application)

    var flowersList = MutableLiveData<List<FlowerModel>>()

    fun getFlowerList(swipeRefresh: SwipeRefreshLayout?, option: Int) {

        flowersRepository.getFlowerList(swipeRefresh, option) {
            flowersList.value = it
        }

    } // getFlowerList



}