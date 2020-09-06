package com.touhidapps.retrofitwithcoroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dev.adnetworkm.CheckNetworkStatus
import com.touhidapps.retrofitwithcoroutine.viewModel.FlowersViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var flowersViewModel : FlowersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        flowersViewModel = ViewModelProvider(this).get(FlowersViewModel::class.java)


        flowersViewModel.flowersList.observe(this, Observer {
            println("${it.get(0)}")
        })

        
        flowersViewModel.getFlowerList(null, 3)

        CheckNetworkStatus.getNetworkLiveData(applicationContext).observe(this, Observer { t ->
            when (t) {
                true -> {
                    // TODO: Handle the connection...
                }
                false -> {
                    // TODO: Handle the connection...
                }
                null -> {
                    // TODO: Handle the connection...
                }
            }
        })




    }



}
