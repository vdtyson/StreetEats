package com.versilistyson.androidstreeteats.presentation.ui.authentication.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.haroldadmin.vector.*

import com.versilistyson.androidstreeteats.R
import com.versilistyson.androidstreeteats.databinding.FragmentLoginBinding
import com.versilistyson.androidstreeteats.di.util.injector
import com.versilistyson.androidstreeteats.presentation.ui.MainSharedViewModel
import com.versilistyson.androidstreeteats.presentation.ui.common.BaseFragment
import javax.inject.Inject


class LoginFragment : BaseFragment<LoginViewModel>() {

    @Inject
    lateinit var mainSharedViewModelFactory: MainSharedViewModel.Factory

    @Inject
    lateinit var loginViewModelFactory: LoginViewModel.Factory

    private val mainSharedViewModel: MainSharedViewModel by activityViewModel { initialState, handle ->
        mainSharedViewModelFactory.create(initialState)
    }


    override val viewModel: LoginViewModel by fragmentViewModel { initialState, handle ->
        loginViewModelFactory.create(initialState)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        val appComponent = requireActivity().injector
        loginViewModelFactory = appComponent.loginViewModelFactory
        mainSharedViewModelFactory = appComponent.mainSharedViewModelFactory

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val loginBinding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        loginBinding.lifecycleOwner = this
        loginBinding.loginViewModel = viewModel
        return loginBinding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        renderState(viewModel) {state ->
            if(state.isLoginSuccessful) {
                mainSharedViewModel.getSignedInFirebaseUser()
                Toast.makeText(this@LoginFragment.context,"Yes",Toast.LENGTH_SHORT).show()
            }
            if(state.showError) {
                val errorMessage = state.errorMessage
                when(state.errorType) {
                    LoginPageState.ErrorType.SERVER -> TODO()
                    LoginPageState.ErrorType.NO_CONNECTION -> TODO()
                    LoginPageState.ErrorType.INVALID_CREDENTIALS -> TODO()
                    null -> TODO()
                }
            }
            if(state.isLoading) {
                TODO("Show progress")
            }
        }
    }
}
