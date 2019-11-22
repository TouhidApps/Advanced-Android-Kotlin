package com.touhidapps.retrofitwithkotlin.view

import android.content.Intent
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

    private var activityMainBinding: ActivityMainBinding? = null
    private var mainActivityViewModel: MainActivityViewModel? = null

    var flowerListAdapter: FlowerListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        // First data load
        activityMainBinding?.swipeRefreshLayoutFlower?.post {

            initAdapter()
            initState()

        }

        // Data load after swipe refresh
        activityMainBinding?.swipeRefreshLayoutFlower?.setOnRefreshListener {

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

        flowerListAdapter?.setItemAction {
            println("Clicked: ${it.contentTitle}")
            var i = Intent(this, DetailsActivity::class.java)
          //  i.putExtra("ITEM_ID", it.contentCode)
            startActivity(i)
        }

    } // initAdapter

    private fun initState() {

        textViewRecyclerError.setOnClickListener {
            mainActivityViewModel?.retry()
        }

        mainActivityViewModel!!.getState().observe(this, Observer { state ->

            progressBarMain.visibility = if (mainActivityViewModel!!.listIsEmpty() && state == LoadingState.LOADING) View.VISIBLE else View.GONE
            textViewRecyclerError.visibility = if (mainActivityViewModel!!.listIsEmpty() && state == LoadingState.ERROR) View.VISIBLE else View.GONE
            if (!mainActivityViewModel!!.listIsEmpty()) {
                flowerListAdapter?.setState(state ?: LoadingState.DONE)
            }

        })

    } // initState



}
