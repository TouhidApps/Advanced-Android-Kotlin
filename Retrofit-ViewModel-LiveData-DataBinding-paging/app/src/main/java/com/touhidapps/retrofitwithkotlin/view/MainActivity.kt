package com.touhidapps.retrofitwithkotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.touhidapps.retrofitwithkotlin.R
import com.touhidapps.retrofitwithkotlin.adapter.FlowerListAdapter
import com.touhidapps.retrofitwithkotlin.databinding.ActivityMainBinding
import com.touhidapps.retrofitwithkotlin.myEnum.LoadingState
import com.touhidapps.retrofitwithkotlin.viewModel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var mainActivityViewModel: MainActivityViewModel? = null
    private var activityMainBinding: ActivityMainBinding? = null
//    private var movies = ArrayList<MovieModel>()
//    private var tempPageNo = 1
    var flowerListAdapter: FlowerListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

//        activityMainBinding?.recyclerViewFlower?.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

//        flowerListAdapter = FlowerListAdapter { mainActivityViewModel?.retry() }
//        activityMainBinding?.recyclerViewFlower?.adapter = flowerListAdapter


//        mainActivityViewModel?.movieRepository?.mutableLiveData?.observe(
//            this,
//            Observer {
//                activityMainBinding?.swipeRefreshLayoutFlower?.isRefreshing = false
//                if (tempPageNo == 1) {
//                    movies.clear()
//                }
//                movies.addAll(it)
//                flowerListAdapter.notifyDataSetChanged()
//            }
//        )

        // First data load
        activityMainBinding?.swipeRefreshLayoutFlower?.post {
//            tempPageNo = 1
//            loadData(tempPageNo)

            initAdapter()
            initState()

        }

        // Data load after swipe refresh
        activityMainBinding?.swipeRefreshLayoutFlower?.setOnRefreshListener {
//            tempPageNo = 1
           // loadData(tempPageNo)

            mainActivityViewModel?.refresh()


        }

        mainActivityViewModel?.dataList?.observe(this, Observer {
            flowerListAdapter?.submitList(mainActivityViewModel?.dataList?.value)
            swipeRefreshLayoutFlower.isRefreshing = false
        })



    } // onCreate

    private fun initAdapter() {
        flowerListAdapter = FlowerListAdapter { mainActivityViewModel!!.retry() }
        recyclerViewFlower.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerViewFlower.adapter = flowerListAdapter
        mainActivityViewModel!!.dataList.observe(this, Observer {
            flowerListAdapter?.submitList(it)
        })
    }

    private fun initState() {
        textViewRecyclerError.setOnClickListener { mainActivityViewModel?.retry() }
        mainActivityViewModel!!.getState().observe(this, Observer { state ->
            println("ERRRR 3 $state")
            progressBarMain.visibility = if (mainActivityViewModel!!.listIsEmpty() && state == LoadingState.LOADING) View.VISIBLE else View.GONE
            textViewRecyclerError.visibility = if (mainActivityViewModel!!.listIsEmpty() && state == LoadingState.ERROR) View.VISIBLE else View.GONE
            if (!mainActivityViewModel!!.listIsEmpty()) {
                flowerListAdapter?.setState(state ?: LoadingState.DONE)
            }
        })



    }

//    private fun loadData(page: Int) {
////        mainActivityViewModel?.getAllMovies(page)
//    }


}
