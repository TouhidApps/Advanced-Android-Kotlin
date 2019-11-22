package com.touhidapps.retrofitwithkotlin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.touhidapps.retrofitwithkotlin.R
import com.touhidapps.retrofitwithkotlin.databinding.RowFlowerBinding
import com.touhidapps.retrofitwithkotlin.model.MovieModel

class FlowerListAdapter(private var items: List<MovieModel>) :
    RecyclerView.Adapter<FlowerListAdapter.MyViewHolder>() {

    var onItemClick: ((MovieModel) -> Unit)? = null

    fun setItemClick(action: (MovieModel) -> Unit) {
        this.onItemClick = action
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val binding = DataBindingUtil.inflate<RowFlowerBinding>(
            LayoutInflater.from(parent.context),
            R.layout.row_flower,
            parent,
            false
        )
        return MyViewHolder(binding)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val item = items[position]
        holder.itemBinding.movie = item

    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class MyViewHolder(@NonNull itemBinding: RowFlowerBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        val itemBinding = itemBinding

        init {

            onItemClick?.let {
                itemBinding.root.setOnClickListener {
                    it(items[adapterPosition])
                }
            }
//        itemBinding.imageView.setOnClickListener {  }
//        itemBinding.root.setOnClickListener {
//            println("Clicked item: $adapterPosition")
//        }
        }
    }

}












