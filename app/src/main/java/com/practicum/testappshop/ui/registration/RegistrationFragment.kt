package com.practicum.testappshop.ui.registration

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.practicum.testappshop.app.App
import com.practicum.testappshop.databinding.RegistrationFragmentBinding
import com.practicum.testappshop.domain.models.User
import com.practicum.testappshop.presentation.states.AuthRegState
import com.practicum.testappshop.presentation.viewmodel.home.HomeViewModel
import com.practicum.testappshop.presentation.viewmodel.registration.RegistrationViewModel
import com.practicum.testappshop.presentation.viewmodel.registration.RegistrationViewModelFactory
import javax.inject.Inject

class RegistrationFragment : Fragment() {
    private var _binding: RegistrationFragmentBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var vmFactory: RegistrationViewModelFactory
    private lateinit var vm: RegistrationViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = RegistrationFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity().application as App).appComponent.inject(this)

        vm = ViewModelProvider(this, vmFactory).get(RegistrationViewModel::class.java)
        binding.toAuth.setOnClickListener {
            findNavController().navigate(RegistrationFragmentDirections.actionRegistrationFragmentToHomeFragment())
        }

        vm.authStateLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                is AuthRegState.Loading -> showLoading()
                is AuthRegState.Authenticated -> findNavController().navigate(
                    RegistrationFragmentDirections.actionRegistrationFragmentToHomeFragment()
                )

                is AuthRegState.Error -> showToast(state.message)
                is AuthRegState.Filling -> showContent()
            }
        }
        showPersonalInfoAdding()
    }

    private fun showToast(text: String) {
        Toast.makeText(
            requireContext(),
            text,
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun showPersonalInfoAdding() {
        binding.loading.visibility = View.GONE
        binding.regNameSurname.visibility = View.VISIBLE
        binding.regEmailPass.visibility = View.GONE

        val namePattern = Regex("^[A-Za-zА-Яа-яЁё]+$")

        val name = binding.name
        val surname = binding.surname
        val patronymic = binding.patronymic

        binding.nextBtn.setOnClickListener {
            if (name.text.isEmpty()) {
                showToast("Имя не может быть пустым")
            } else if (surname.text.isEmpty()) {
                showToast("Фамилия не может быть пустой")
            } else if (patronymic.text.isEmpty()) {
                showToast("Отчество не может быть пустым")
            } else if (!namePattern.matches(name.text)) {
                showToast("Неверный формат имени")
            } else if (!namePattern.matches(surname.text)) {
                showToast("Неверный формат фамилии")
            } else if (!namePattern.matches(patronymic.text)) {
                showToast("Неверный формат отчества")
            } else {
                showSignUp(
                    User(
                        name = name.text.toString(),
                        surname = surname.text.toString(),
                        patronymic = patronymic.text.toString()
                    )
                )
            }
        }
    }

    private fun showSignUp(user: User) {
        binding.regNameSurname.visibility = View.GONE
        binding.regEmailPass.visibility = View.VISIBLE

        binding.finishReg.setOnClickListener {
            val emailPattern = Regex(
                "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$"
            )
            val email = binding.login.text.toString()
            val pass = binding.password.text.toString()
            val repeatPass = binding.repeatPassword.text.toString()

            if (email.isEmpty()) {
                showToast("Логин не может быть пустым")
            } else if (pass.isEmpty()) {
                showToast("Пароль не может быть пустым")
            } else if (pass != repeatPass) {
                showToast("Пароли не совпадают")
            } else if (!emailPattern.matches(email)) {
                showToast("Неверный формат эл. почты")
            } else {
                vm.register(email, pass, user)
            }
        }
    }

    private fun showLoading() {
        binding.regNameSurname.visibility = View.GONE
        binding.regEmailPass.visibility = View.GONE
        binding.loading.visibility = View.VISIBLE
    }

    private fun showContent() {
        binding.loading.visibility = View.GONE
        binding.regNameSurname.visibility = View.VISIBLE
        binding.regEmailPass.visibility = View.GONE
    }
}
