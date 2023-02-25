package com.arif.spliff.ui.home

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.arif.spliff.R
import com.arif.spliff.databinding.FragmentHomeBinding
import com.arif.spliff.entity.Product
import com.arif.spliff.ui.all_products.AllProductsFragment
import com.arif.spliff.ui.all_products.ItemListener
import com.arif.spliff.ui.all_products.ProductAdapter
import com.arif.spliff.ui.all_products.ShowCartFragment
import dagger.hilt.android.AndroidEntryPoint
import org.w3c.dom.Text

@AndroidEntryPoint
class HomeFragment : Fragment(), ItemListener {

    lateinit var viewModel: HomeViewModel

    lateinit var binding: FragmentHomeBinding
    lateinit var adapter: ProductAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        adapter = ProductAdapter(requireActivity(), this)
        binding.productRcv.adapter = adapter

        binding.search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                if (!s.isNullOrBlank()) {

                    binding.productRcv.visibility = View.GONE
                    binding.mainLayout.visibility = View.VISIBLE
                } else {
//                    binding.productRcv.visibility = View.VISIBLE
//                    binding.mainLayout.visibility = View.GONE

                }

            }

            override fun afterTextChanged(s: Editable?) {

            }


        })

        binding.search.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                Log.i("TAG", "onCreateView:${v.text} ")

                viewModel.responseProductSingle("${v.text.toString().trim()}")
                binding.productRcv.visibility = View.VISIBLE
                binding.mainLayout.visibility = View.GONE

                return@OnEditorActionListener true
            }
            false
        })
        viewModel.queryData.observe(viewLifecycleOwner) {

            Log.i("TAG", "size : ${it.size}")

            binding.productRcv.visibility = View.VISIBLE
            binding.mainLayout.visibility = View.GONE



            adapter.submitList(it)

        }

        binding.vapes.setOnClickListener {
          findNavController().navigate(R.id.action_homeFragment_to_allProductsFragment)
        }
        binding.flower.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_allProductsFragment)
        }
        binding.edibles.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_allProductsFragment)
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