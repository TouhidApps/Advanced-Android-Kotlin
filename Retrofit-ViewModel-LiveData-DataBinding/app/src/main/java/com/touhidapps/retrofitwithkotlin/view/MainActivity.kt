package com.touhidapps.retrofitwithkotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.touhidapps.retrofitwithkotlin.R
import com.touhidapps.retrofitwithkotlin.adapter.FlowerListAdapter
import com.touhidapps.retrofitwithkotlin.databinding.ActivityMainBinding
import com.touhidapps.retrofitwithkotlin.model.MovieModel
import com.touhidapps.retrofitwithkotlin.viewmodel.MainActivityViewModel


class MainActivity : AppCompatActivity() {

    private var mainActivityViewModel: MainActivityViewModel? = null
    private var activityMainBinding: ActivityMainBinding? = null
    private var movies = ArrayList<MovieModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        activityMainBinding?.recyclerViewFlower?.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        val flowerListAdapter = FlowerListAdapter(movies)
        flowerListAdapter.setItemClick {
            Toast.makeText(this, "Item Clicked ${it.contentTitle}", Toast.LENGTH_SHORT).show()
        }
        activityMainBinding?.recyclerViewFlower?.adapter = flowerListAdapter

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        mainActivityViewModel?.movieRepository?.mutableLiveData?.observe(
            this,
            Observer {
                movies.addAll(it)
                flowerListAdapter.notifyDataSetChanged()
            }
        )



        loadData(1)
        loadData(2)


    } // onCreate

    fun loadData(page: Int) {
        mainActivityViewModel?.getAllMovies(page)
    }


}
