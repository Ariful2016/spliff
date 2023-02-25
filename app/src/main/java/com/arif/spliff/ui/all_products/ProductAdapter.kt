package com.arif.spliff.ui.all_products

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arif.spliff.databinding.ItemProductBinding
import com.arif.spliff.entity.Product
import com.arif.spliff.ui.DetailsActivity
import com.bumptech.glide.Glide

class ProductAdapter(
    var context: Context,
    var cartListener: ItemListener
) :
    ListAdapter<Product, ProductAdapter.ProductViewHolder>(comparator) {

    inner class ProductViewHolder(var binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        getItem(position).let {


            holder.binding.run {

                txt.text = it.category
                name.text = it.title
                price.text = "$${it.price}"
                Glide.with(context).load(it.image).into(img)

                addToCart.setOnClickListener { _ ->
                    cartListener.addToCart(it)
                }

            }



            holder.itemView.setOnClickListener { _ ->

                val intent = Intent(context, DetailsActivity::class.java)
                intent.putExtra("prd", it)
                context.startActivity(intent)

            }


        }
    }

    companion object {
        var comparator = object : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {

                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem == newItem
            }

        }


    }

}