package com.touhidapps.retrofitwithkotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.touhidapps.retrofitwithkotlin.R
import com.touhidapps.retrofitwithkotlin.databinding.ActivityDetailsBinding
import com.touhidapps.retrofitwithkotlin.model.Servicedetails
import com.touhidapps.retrofitwithkotlin.viewModel.DetailActivityViewModel

class DetailsActivity : AppCompatActivity() {

    private var mBinding: ActivityDetailsBinding? = null
    private var mViewModel: DetailActivityViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_details)
        mViewModel = ViewModelProviders.of(this).get(DetailActivityViewModel::class.java)


        mBinding?.myData = Servicedetails("", "", "", "")

        mViewModel?.mutableLiveData?.observe(this, Observer {
            mBinding?.myData = it.servicedetails[0]
        })

        // 27 is demo test id for this api
        mViewModel?.getAllMovies("27")

    }






}
