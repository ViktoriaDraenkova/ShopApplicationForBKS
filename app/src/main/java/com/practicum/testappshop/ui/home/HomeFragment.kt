package com.practicum.testappshop.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import com.practicum.testappshop.app.App
import com.practicum.testappshop.databinding.HomeFragmentBinding
import com.practicum.testappshop.presentation.states.ProductsScreenState
import com.practicum.testappshop.presentation.viewmodel.home.HomeViewModelFactory
import com.practicum.testappshop.presentation.viewmodel.home.HomeViewModel
import javax.inject.Inject

class HomeFragment : Fragment() {
    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var vmFactory: HomeViewModelFactory
    private lateinit var vm: HomeViewModel
    private var productsViewAdapter: ProductsViewAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity().application as App).appComponent.inject(this)
        vm = ViewModelProvider(this, vmFactory).get(HomeViewModel::class.java)

        productsViewAdapter = ProductsViewAdapter(
            { vm.increaseInCart(it) }, { vm.decreaseInCart(it) },
            {
                val productJson = Gson().toJson(it) // Сериализация объекта в JSON
                val action =
                    HomeFragmentDirections.actionHomeFragmentToProductDetailsFragment(productJson)
                findNavController().navigate(action)
            }
        )

        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = productsViewAdapter
        }

        vm.getScreenLiveData().observe(viewLifecycleOwner) { screenState ->
            hideAll()
            when (screenState) {
                is ProductsScreenState.Content -> {
                    productsViewAdapter?.setProducts(
                        screenState.data,
                        vm.getCountInCart(screenState.data)
                    )
                    binding.recyclerView.visibility = View.VISIBLE
                }

                is ProductsScreenState.Loading -> {
                    binding.loading.visibility = View.VISIBLE
                }
                is ProductsScreenState.Error -> {
                    binding.noInternetConnection.visibility = View.VISIBLE
                }
            }
        }

        vm.getProducts()
    }

    private fun hideAll() {
        binding.error.visibility = View.GONE
        binding.loading.visibility = View.GONE
        binding.noInternetConnection.visibility = View.GONE
        binding.recyclerView.visibility = View.GONE
    }
}