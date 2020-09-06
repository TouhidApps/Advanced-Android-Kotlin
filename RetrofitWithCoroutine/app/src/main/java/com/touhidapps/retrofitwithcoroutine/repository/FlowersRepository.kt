package com.touhidapps.retrofitwithcoroutine.repository

import android.app.Application
import android.view.View
import android.widget.Toast
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.touhidapps.retrofitwithcoroutine.model.FlowerModel
import com.touhidapps.retrofitwithcoroutine.networkService.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class FlowersRepository(var application: Application) {

    fun getFlowerList(swipeRefresh: SwipeRefreshLayout?, option: Int, completion: (List<FlowerModel>) -> Unit) {

        swipeRefresh?.isRefreshing = true
        GlobalScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.retrofit.getFlowerList(option)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    swipeRefresh?.isRefreshing = false
                    response.body()?.let {
                        completion(it)
                    }
                } else {
                    swipeRefresh?.isRefreshing = true
                    println(response.message())
                    Toast.makeText(application, "Something wrong.", Toast.LENGTH_SHORT).show()
                }
            }
        }

    } // getFlowerList

}