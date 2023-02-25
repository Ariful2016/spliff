package com.arif.spliff.ui.cart

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.arif.spliff.databinding.FragmentCartBinding
import com.arif.spliff.entity.CartTemp
import com.arif.spliff.ui.PaymentActivity

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment() {

    lateinit var binding: FragmentCartBinding

    lateinit var viewModel: CartViewModel

    var carts = mutableSetOf<CartTemp>()

    lateinit var adapter: CartAdapter

    var amountToPay = 0.0;


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[CartViewModel::class.java]

        adapter = CartAdapter(requireContext())
        binding.cartRecycler.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.responseAllCart().observe(viewLifecycleOwner) { list ->
            carts.clear()

            list.forEach {
                val product = viewModel.responseProductSingle("${it.productID}")

                val totalPrice = it.noOfProduct * product.price

                amountToPay += totalPrice


                val cart = CartTemp(
                    it.id,
                    product.title,
                    product.price,
                    product.description,
                    product.category,
                    product.image,
                    it.noOfProduct.toDouble(),
                    totalPrice

                )
                carts.add(cart)


            }

            adapter.submitList(carts.toList())

            binding.totalPrice.text = "$${amountToPay}"

        }

        binding.payBtn.setOnClickListener {
            val intent = Intent(context, PaymentActivity::class.java)
            intent.putExtra("price", amountToPay)
            startActivity(intent)
        }


    }

}