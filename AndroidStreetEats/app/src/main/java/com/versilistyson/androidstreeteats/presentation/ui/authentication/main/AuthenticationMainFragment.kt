package com.versilistyson.androidstreeteats.presentation.ui.authentication.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.versilistyson.androidstreeteats.databinding.FragmentAuthenticationMainBinding
import com.versilistyson.androidstreeteats.presentation.ui.common.BaseFragment
import kotlinx.android.synthetic.main.fragment_authentication_main.*

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

        authenticationMainBinding.bttnToSignIn.setOnClickListener {
            val toSignInAction =
                AuthenticationMainFragmentDirections.actionAuthenticationMainToLoginFragment2()
            navController.navigate(toSignInAction)
        }

        authenticationMainBinding.bttnToSignup.setOnClickListener {
            val toSignUpAction =
                AuthenticationMainFragmentDirections.actionAuthenticationMainToSignupFragment()
            navController.navigate(toSignUpAction)
        }
    }
}
