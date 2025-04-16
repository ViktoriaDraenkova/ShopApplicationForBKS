package com.practicum.testappshop.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.practicum.testappshop.app.App
import com.practicum.testappshop.databinding.CartFragmentBinding
import com.practicum.testappshop.presentation.viewmodel.cart.CartViewModel
import com.practicum.testappshop.presentation.viewmodel.cart.CartViewModelFactory
import com.practicum.testappshop.ui.home.CartProductsViewAdapter
import com.practicum.testappshop.ui.home.HomeFragmentDirections
import javax.inject.Inject

class CartFragment : Fragment() {
    private var _binding: CartFragmentBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var vmFactory: CartViewModelFactory
    private lateinit var vm: CartViewModel
    private var cartViewAdapter: CartProductsViewAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CartFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity().application as App).appComponent.inject(this)
        vm = ViewModelProvider(this, vmFactory).get(CartViewModel::class.java)

        cartViewAdapter = CartProductsViewAdapter(
            { vm.increaseInCart(it) }, { vm.decreaseInCart(it) },
            {
                val productJson = Gson().toJson(it)
                val action =
                    CartFragmentDirections.actionCartFragmentToProductDetailsFragment(productJson)
                findNavController().navigate(action)
            }
        )

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = cartViewAdapter
        }

        cartViewAdapter!!.setProducts(
            vm.getCart().map { it.first },
            vm.getCart().map { it.second }
        )

        binding.buyBtn.setOnClickListener {
            vm.buy {
                cartViewAdapter!!.clearProducts()
            }
        }
    }
}