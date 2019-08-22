package com.touhidapps.recyclerviewwithkotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.touhidapps.recyclerviewwithkotlin.R
import com.touhidapps.recyclerviewwithkotlin.model.FlowerModel

class FlowerListAdapter(private var items : List<FlowerModel>) : RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.row_flower, parent, false)
        return MyViewHolder(mView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = items[position]
        Glide.with(holder.imageViewThumb.context).load(item.imageUrl).into(holder.imageViewThumb)
        holder.textViewName.text = item.name
    }

    override fun getItemCount(): Int {
        return items.size
    }

}

class MyViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val imageViewThumb: ImageView = view.findViewById(R.id.imageView)
    val textViewName: TextView = view.findViewById(R.id.textView)
}











