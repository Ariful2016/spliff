package com.arif.spliff.ui.all_products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.arif.spliff.databinding.AddCartPopupBinding
import com.arif.spliff.entity.Cart
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowCartFragment : DialogFragment() {

    lateinit var binding: AddCartPopupBinding
    lateinit var viewModel: ProductViewModel
    var numberOfProduct = 0


    fun newInstance(id: Int): ShowCartFragment? {
        val frag = ShowCartFragment()
        val args = Bundle()
        args.putInt("id", id)
        frag.arguments = args
        return frag
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AddCartPopupBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[ProductViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val productId = requireArguments().getInt("id")

        binding.addImg.setOnClickListener {
            numberOfProduct++
            binding.quantityTxt.text = "$numberOfProduct"

            Toast.makeText(requireContext(), "$numberOfProduct", Toast.LENGTH_SHORT).show()
        }


        binding.minusImg.setOnClickListener {
            if (numberOfProduct >= 0) {
                numberOfProduct--
                binding.quantityTxt.text = "$numberOfProduct"

                Toast.makeText(requireContext(), "$numberOfProduct", Toast.LENGTH_SHORT).show()
            }




        }

        binding.addToCart.setOnClickListener {
            val cart = Cart(productID = productId, noOfProduct = numberOfProduct, id = 0)
            viewModel.insertCart(cart)
        }


    }

}