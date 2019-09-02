package com.touhidapps.retrofitwithkotlin.repository

import android.app.Application
import android.widget.Toast
import com.touhidapps.retrofitwithkotlin.model.ServiceModel
import com.touhidapps.retrofitwithkotlin.networkService.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class MovieRepository(var application: Application) {


    // not used because of paging library
    fun loadData(id: String, action: (ServiceModel) -> Unit) {

//        val rBody = HashMap<String, Any>()
//        rBody["CatCode"] = "MV"
//        rBody["PageTotal"] = page

        RetrofitClient.instance()?.getServiceDetail(id)
            ?.subscribeOn(Schedulers.newThread())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(
            { mData ->
                action(mData)
            },
            {
                Toast.makeText(application, "Something wrong.", Toast.LENGTH_SHORT).show()
            }
        )


    } // loadData


}


