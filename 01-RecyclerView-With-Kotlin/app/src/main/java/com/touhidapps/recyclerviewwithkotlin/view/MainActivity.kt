package com.touhidapps.recyclerviewwithkotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.touhidapps.recyclerviewwithkotlin.R
import com.touhidapps.recyclerviewwithkotlin.adapter.FlowerListAdapter
import com.touhidapps.recyclerviewwithkotlin.model.FlowerModel

class MainActivity : AppCompatActivity() {

    private var recyclerViewFlower: RecyclerView? = null
    private var flowerListAdapter: FlowerListAdapter? = null
    private var flowers = ArrayList<FlowerModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerViewFlower = findViewById(R.id.recyclerViewFlower)
        recyclerViewFlower?.layoutManager = GridLayoutManager(this, 2)
//        recyclerViewFlower?.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        flowerListAdapter = FlowerListAdapter(flowers)
        recyclerViewFlower?.adapter = flowerListAdapter


        loadData()
        loadData()
        flowerListAdapter?.notifyDataSetChanged()

    } // onCreate


    private fun loadData() {

        flowers.add(
            FlowerModel(
                0,
                "Rose",
                "https://site-files.fiftyflowers.com/FiftyFlowers/Image/Product/Hot-Lady2-350_de950408.jpg"
            )
        )
        flowers.add(
            FlowerModel(
                1,
                "Dandelion",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/5/54/TaraxacumOfficinaleSeed.JPG/220px-TaraxacumOfficinaleSeed.JPG"
            )
        )
        flowers.add(
            FlowerModel(
                2,
                "Canna Lilies",
                "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/red-canna-flower-royalty-free-image-174932106-1554841383.jpg?crop=0.635xw:0.953xh;0.357xw,0.0470xh&resize=768:*"
            )
        )
        flowers.add(
            FlowerModel(
                3,
                "Spider Flowers",
                "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/cleome-spinosa-royalty-free-image-949867034-1554736304.jpg?crop=0.664xw:1.00xh;0,0&resize=768:*"
            )
        )
        flowers.add(
            FlowerModel(
                4,
                "Lily of the Nile",
                "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/baby-pete-lily-of-the-nile-1554757884.jpg?crop=1xw:0.6666666666666666xh;center,top&resize=768:*"
            )
        )
        flowers.add(
            FlowerModel(
                5,
                "Wheat Celosia",
                "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/colorful-celosia-flower-royalty-free-image-533478895-1554736063.jpg?crop=0.668xw:1.00xh;0.0355xw,0&resize=768:*"
            )
        )
        flowers.add(
            FlowerModel(
                6,
                "Garden Cosmos",
                "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/colorful-cosmos-flowers-royalty-free-image-157190551-1554736448.jpg?crop=0.676xw:1.00xh;0.324xw,0&resize=768:*"
            )
        )

    } // loadData


}
