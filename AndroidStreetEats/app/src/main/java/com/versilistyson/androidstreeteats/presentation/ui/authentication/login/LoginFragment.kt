package com.versilistyson.androidstreeteats.presentation.ui.authentication.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.haroldadmin.vector.fragmentViewModel
import com.haroldadmin.vector.withState

import com.versilistyson.androidstreeteats.R
import com.versilistyson.androidstreeteats.databinding.FragmentSignInScreenBinding
import com.versilistyson.androidstreeteats.presentation.ui.common.BaseFragment
import javax.inject.Inject


class LoginFragment : BaseFragment<LoginViewModel>() {

    @Inject
    lateinit var loginViewModelFactory: LoginViewModel.Factory


    override val viewModel: LoginViewModel by fragmentViewModel { initialState, handle ->
        loginViewModelFactory.create(initialState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val loginBinding = FragmentSignInScreenBinding.inflate(layoutInflater)
        loginBinding.lifecycleOwner = this
        loginBinding.loginViewModel = viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        renderState(viewModel) {state ->
            if(state.isLoginSuccessful) {
                TODO()
            }
            if(state.showError) {
                TODO()
            }
            if(state.isLoading) {
                TODO()
            }
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}
