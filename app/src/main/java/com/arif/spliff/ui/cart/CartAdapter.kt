package com.arif.spliff.ui.cart

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arif.spliff.databinding.ItemCartBinding
import com.arif.spliff.entity.CartTemp
import com.bumptech.glide.Glide

class CartAdapter(var context: Context) :
    ListAdapter<CartTemp, CartAdapter.ProductViewHolder>(comparator) {

    inner class ProductViewHolder(var binding: ItemCartBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            ItemCartBinding.inflate(
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
                price.text = "$${it.price} X ${it.qty} = $${it.price * it.qty}"
                quantityTxt.text = "${it.qty.toInt()}"
                Glide.with(context).load(it.image).into(img)


            }

        }
    }

    companion object {
        var comparator = object : DiffUtil.ItemCallback<CartTemp>() {
            override fun areItemsTheSame(oldItem: CartTemp, newItem: CartTemp): Boolean {

                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: CartTemp, newItem: CartTemp): Boolean {
                return oldItem == newItem
            }

        }


    }

}