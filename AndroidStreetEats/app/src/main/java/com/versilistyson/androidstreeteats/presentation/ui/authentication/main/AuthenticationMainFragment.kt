package com.versilistyson.androidstreeteats.presentation.ui.authentication.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.versilistyson.androidstreeteats.R

import com.versilistyson.androidstreeteats.databinding.FragmentAuthenticationMainBinding
import com.versilistyson.androidstreeteats.presentation.common.fragment.BaseFragment

class AuthenticationMainFragment : BaseFragment() {

    lateinit var authenticationMainBinding: FragmentAuthenticationMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        authenticationMainBinding = FragmentAuthenticationMainBinding.inflate(layoutInflater, container, false)
        authenticationMainBinding.lifecycleOwner = this
        return authenticationMainBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupButtons()
    }

    private fun setupButtons() {
        authenticationMainBinding.bttnToSignIn.setOnClickListener {
            navController.navigate(R.id.loginFragment)
        }

        authenticationMainBinding.bttnToSignup.setOnClickListener {
            navController.navigate(R.id.signupFragment)
        }
    }
}
