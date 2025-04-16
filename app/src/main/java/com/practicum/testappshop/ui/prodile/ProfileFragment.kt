package com.practicum.testappshop.ui.prodile

import PurchaseSummaryDialog
import PurchasesViewAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.practicum.testappshop.app.App
import com.practicum.testappshop.databinding.ProfileFragmentBinding
import com.practicum.testappshop.presentation.viewmodel.profile.ProfileViewModel
import com.practicum.testappshop.presentation.viewmodel.profile.ProfileViewModelFactory
import javax.inject.Inject

class ProfileFragment : Fragment() {
    private var _binding: ProfileFragmentBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var vmFactory: ProfileViewModelFactory
    private lateinit var vm: ProfileViewModel
    private var purchasesViewAdapter: PurchasesViewAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ProfileFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity().application as App).appComponent.inject(this)
        vm = ViewModelProvider(this, vmFactory).get(ProfileViewModel::class.java)

        purchasesViewAdapter = PurchasesViewAdapter{
            PurchaseSummaryDialog(it).show(parentFragmentManager, null)
        }
        binding.purchasesRecyclerView.adapter = purchasesViewAdapter
        binding.purchasesRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        vm.personalLiveData.observe(viewLifecycleOwner) { result ->
            if (result != null) {
                binding.name.text = "${result.surname} ${result.name} ${result.patronymic}"
                vm.getPurchases()
            }
        }

        vm.setPersonalInfo()

        binding.cleanHistory.setOnClickListener {
            showToast("В ТЗ такого не было) Так что она просто украшает экран)")
        }

        vm.purchasesLiveData.observe(viewLifecycleOwner) { result ->
            Log.d("Got purchases", result.toString())
            purchasesViewAdapter?.setPurchases(result)
        }


    }

    private fun showToast(text: String) {
        Toast.makeText(
            requireContext(),
            text,
            Toast.LENGTH_SHORT
        ).show()
    }

}