package com.versilistyson.androidstreeteats.presentation.ui.authentication.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.haroldadmin.vector.*

import com.versilistyson.androidstreeteats.databinding.FragmentLoginBinding
import com.versilistyson.androidstreeteats.di.util.injector
import com.versilistyson.androidstreeteats.domain.exception.Failure
import com.versilistyson.androidstreeteats.presentation.ui.common.BaseVectorFragment
import javax.inject.Inject


class LoginFragment : BaseVectorFragment<LoginViewModel>() {


    @Inject
    lateinit var loginViewModelFactory: LoginViewModel.Factory

    //lateinit var mainSharedViewModel: MainSharedViewModel


    override val viewModel: LoginViewModel by fragmentViewModel { initialState, handle ->
        loginViewModelFactory.create(initialState)
    }

    private lateinit var loginBinding: FragmentLoginBinding

    override fun onAttach(context: Context) {
        this.findNavController()
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val appComponent = requireActivity().injector
        loginViewModelFactory = appComponent.loginViewModelFactory
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loginBinding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        loginBinding.lifecycleOwner = this
        loginBinding.loginViewModel = viewModel
        return loginBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        renderState(viewModel) {state ->
            renderLoadingState(state.isLoading)
            renderErrorState(state.showError, state.errorMessage, state.failure)
            renderLoginSuccessState(state.isLoginSuccessful, state.uid)
        }
    }

    override fun renderLoadingState(isLoading: Boolean) {
        if(isLoading) {
            loginBinding.overlayForProgressbar.visibility = View.VISIBLE
            loginBinding.loginProgressBar.animate()
        } else {
            loginBinding.overlayForProgressbar.visibility = View.GONE
            loginBinding.loginProgressBar.clearAnimation()
        }
    }

    override fun renderErrorState(showError: Boolean, errorMessage: String?, failure: Failure?) {
        Toast.makeText(this.context, errorMessage, Toast.LENGTH_SHORT).show()
    }
    private fun renderLoginSuccessState(isLoginSuccessful: Boolean, uid: String) {
        if(isLoginSuccessful) {
            Toast.makeText(this@LoginFragment.context,"uid: $uid",Toast.LENGTH_SHORT).show()
        }
    }
}
