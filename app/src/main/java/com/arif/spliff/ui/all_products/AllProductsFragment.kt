package com.arif.spliff.ui.all_products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.arif.spliff.databinding.FragmentAllProductsBinding
import com.arif.spliff.entity.Product
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AllProductsFragment : Fragment(), ItemListener {

    lateinit var binding: FragmentAllProductsBinding

    lateinit var viewModel: ProductViewModel

    lateinit var adapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentAllProductsBinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProvider(this)[ProductViewModel::class.java]


        adapter = ProductAdapter(requireActivity(), this)
        binding.productRcv.adapter = adapter




        viewModel.responseAllProduct().observe(viewLifecycleOwner) {

            if (it.isEmpty()) {
                GlobalScope.launch {
                    viewModel.repository.productCache()
                }

            }


            adapter.submitList(it)
        }






        return binding.root
    }

    override fun addToCart(product: Product) {
        val fm: FragmentManager = parentFragmentManager
        val editNameDialogFragment: ShowCartFragment =
            ShowCartFragment().newInstance(product.id)!!
        editNameDialogFragment.show(fm, "fragment")

    }


}