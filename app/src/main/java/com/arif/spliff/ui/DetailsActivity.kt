package com.arif.spliff.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arif.spliff.R
import com.arif.spliff.databinding.ActivityDetailsBinding
import com.arif.spliff.entity.Product
import com.bumptech.glide.Glide

class DetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.hasExtra("prd")){
            var product: Product? = intent.getParcelableExtra("prd")

            product?.run {

                binding.name.text = title
                binding.priceTxt.text = "$ "+price.toString()
                binding.totalPrice.text = "$ "+price.toString()
                binding.discription.text = description
                Glide.with(this@DetailsActivity).load(product.image).into(binding.img)

            }



        }



        binding.back.setOnClickListener {
            finish()
        }


    }
}