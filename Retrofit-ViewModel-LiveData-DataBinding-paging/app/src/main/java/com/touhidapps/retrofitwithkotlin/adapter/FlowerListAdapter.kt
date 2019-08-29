package com.touhidapps.retrofitwithkotlin.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.touhidapps.retrofitwithkotlin.R
import com.touhidapps.retrofitwithkotlin.databinding.RowFlowerBinding
import com.touhidapps.retrofitwithkotlin.databinding.RowFooterBinding
import com.touhidapps.retrofitwithkotlin.model.MovieModel
import com.touhidapps.retrofitwithkotlin.myEnum.LoadingState
import com.touhidapps.retrofitwithkotlin.view.MainActivity

class FlowerListAdapter(private val retry: () -> Unit) :
    PagedListAdapter<MovieModel, RecyclerView.ViewHolder>(myDiffCallback) {

    private val DATA_VIEW_TYPE = 1
    private val FOOTER_VIEW_TYPE = 2

    private var state = LoadingState.LOADING

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return if (viewType == DATA_VIEW_TYPE) {
            val bindingData: RowFlowerBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.row_flower, parent, false)
            MyDataViewHolder(bindingData)
        } else {
            val bindingFooter: RowFooterBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.row_footer, parent, false)
            MyFooterViewHolder(retry, bindingFooter)
        }

    } // onCreateViewHolder

    override fun getItemViewType(position: Int): Int {
        return if (position < super.getItemCount()) {
            DATA_VIEW_TYPE
        } else {
            FOOTER_VIEW_TYPE
        }
    } // getItemViewType

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (getItemViewType(position) == DATA_VIEW_TYPE) {
            (holder as MyDataViewHolder).mBinding.movie = getItem(position)
//            (holder as MyDataViewHolder).mBinding.movie = items[position]
        } else {

            // retry
            if (state == LoadingState.ERROR) {
                println("ttt-- 3 ")
                (holder as MyFooterViewHolder).mBinding.footerTextVisible = false
            } else{
                println("ttt-- 4 ")
                (holder as MyFooterViewHolder).mBinding.footerTextVisible = true
            }

            // loading
            if (state == LoadingState.LOADING) {
                println("ttt-- 1 ")
                (holder as MyFooterViewHolder).mBinding.progressVisible = false
            } else{
                println("ttt-- 2 ")
                (holder as MyFooterViewHolder).mBinding.progressVisible = true
            }



        }

    } // onBindViewHolder

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasFooter()) 1 else 0
    }

    private fun hasFooter(): Boolean {
        return super.getItemCount() != 0 && (state == LoadingState.LOADING || state == LoadingState.ERROR)
    }

    fun setState(state: LoadingState) {
        this.state = state
        notifyItemChanged(super.getItemCount())
    }

    companion object {

        val myDiffCallback = object : DiffUtil.ItemCallback<MovieModel>() {
            override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
                return oldItem.contentTitle == newItem.contentTitle
            }

            override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
                return oldItem == newItem
            }
        }
    }

} // FlowerListAdapter

class MyDataViewHolder(@NonNull val mBinding: RowFlowerBinding) : RecyclerView.ViewHolder(mBinding.root) {

    init {
        mBinding.root.setOnClickListener {
            println("Clicked item: $adapterPosition")
            mBinding.root.context.startActivity(Intent(mBinding.root.context, MainActivity::class.java))
        }
    }

} // MyDataViewHolder

class MyFooterViewHolder(retry: () -> Unit, @NonNull val mBinding: RowFooterBinding) : RecyclerView.ViewHolder(mBinding.root) {

    init {
        mBinding.textViewFooterMessage.setOnClickListener { retry() }
    }

} // MyFooterViewHolder









