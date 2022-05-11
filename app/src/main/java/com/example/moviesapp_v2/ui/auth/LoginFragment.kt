package com.example.moviesapp_v2.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.moviesapp_v2.core.Resource
import androidx.navigation.fragment.findNavController
import com.example.moviesapp_v2.R
import com.example.moviesapp_v2.data.remote.auth.LoginDataSource
import com.example.moviesapp_v2.databinding.FragmentLoginBinding
import com.example.moviesapp_v2.presentation.auth.LoginScreenViewModel
import com.example.moviesapp_v2.presentation.auth.LoginScreenViewModelFactory
import com.example.moviesapp_v2.repository.auth.LoginRepoImpl
import com.google.firebase.auth.FirebaseAuth
import kotlin.math.sign


class LoginFragment : Fragment(R.layout.fragment_login) {

    private val firebaseAuth by lazy { FirebaseAuth.getInstance() }
    private lateinit var binding: FragmentLoginBinding
    private val viewModel by viewModels<LoginScreenViewModel>
    { LoginScreenViewModelFactory(LoginRepoImpl(
        LoginDataSource()
    )) }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

        //Ckecking if user is logged in
        isUserLoggedIn()

        doLogin()
    }

    private fun isUserLoggedIn() {
        firebaseAuth.currentUser?.let { user ->
            findNavController().navigate(R.id.action_loginFragment_to_movieFragment)
        }
    }

    private fun doLogin() {
        binding.btnLogin.setOnClickListener {
            val email = binding.editTextEmail.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()
            validateCredentials(email, password)
            signIn(email, password)
        }
    }

    private fun validateCredentials(email: String, password: String) {
        if (email.isEmpty()) {
            binding.editTextEmail.error = "E-mail is empty"
            return
        }
        if (password.isEmpty()) {
            binding.editTextPassword.error = "Password is empty"
            return
        }
    }

    private fun signIn(email: String, password: String) {
        viewModel.signIn(email,password).observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is Resource.Loading ->{
                    binding.progressBar.visibility = View.VISIBLE
                    binding.btnLogin.isEnabled = false
                }

                is Resource.Success ->{
                    binding.progressBar.visibility = View.GONE
                    findNavController().navigate(R.id.action_loginFragment_to_movieFragment)
                }

                is Resource.Failure ->{
                    binding.progressBar.visibility = View.GONE
                    binding.btnLogin.isEnabled = true
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()

                }
            }

        })
    }
}
/*private fun signIn(email: String, password: String){
        viewModel.signIn(email,password).observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.btnSignin.isEnabled = false
                }
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        "Welcome ${result.data?.email}",
                        Toast.LENGTH_SHORT
                    ).show()
                    if(result.data?.displayName.isNullOrEmpty()) {
                        findNavController().navigate(R.id.action_loginFragment_to_setupProfileFragment)
                    }else{
                        findNavController().navigate(R.id.action_loginFragment_to_homeScreenFragment)
                    }
                }
                is Result.Failure -> {
                    binding.btnSignin.isEnabled = true
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        "Error: ${result.exception}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })

* */