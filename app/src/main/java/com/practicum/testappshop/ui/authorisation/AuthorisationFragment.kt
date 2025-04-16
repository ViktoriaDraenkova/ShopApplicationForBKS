package com.practicum.testappshop.ui.authorisation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.practicum.testappshop.app.App
import com.practicum.testappshop.databinding.AuthorisationFragmentBinding
import com.practicum.testappshop.presentation.states.AuthRegState
import com.practicum.testappshop.presentation.viewmodel.authorisation.AuthorisationViewModel
import com.practicum.testappshop.presentation.viewmodel.authorisation.AuthorisationViewModelFactory
import javax.inject.Inject

class AuthorisationFragment : Fragment() {
    private var _binding: AuthorisationFragmentBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var vmFactory: AuthorisationViewModelFactory
    private lateinit var vm: AuthorisationViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AuthorisationFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity().application as App).appComponent.inject(this)

        vm = ViewModelProvider(this, vmFactory).get(AuthorisationViewModel::class.java)
        vm.userLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                is AuthRegState.Loading -> showLoading()
                is AuthRegState.Authenticated -> findNavController().navigate(AuthorisationFragmentDirections.actionAuthorisationFragmentToHomeFragment())
                is AuthRegState.Error -> showToast(state.message)
                is AuthRegState.Filling -> showContent()
            }
        }

        vm.checkAuthorization()

        binding.regBtn.setOnClickListener {
            findNavController().navigate(AuthorisationFragmentDirections.actionAuthorisationFragmentToRegistrationFragment())
        }

        binding.nextBtnAuth.setOnClickListener {
            val email = binding.login.text.toString()
            val pass = binding.password.text.toString()

            if (email.isEmpty()) {
                showToast("Логин не может быть пустым")
            } else if (pass.isEmpty()) {
                showToast("Пароль не может быть пустым")
            } else {
                vm.login(email, pass)
            }
        }
    }

    private fun showToast(text: String) {
        Toast.makeText(
            requireContext(),
            text,
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun showLoading() {
        binding.authEmailLogin.visibility = View.GONE
        binding.loading.visibility = View.VISIBLE
    }

    private fun showContent() {
        binding.loading.visibility = View.GONE
        binding.authEmailLogin.visibility = View.VISIBLE
    }
}
