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
            if(state.isLoginSuccessful) {
                //mainSharedViewModel.getSignedInFirebaseUser()
                Toast.makeText(this@LoginFragment.context,"Sign in successful",Toast.LENGTH_SHORT).show()
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
                loginBinding.overlayForProgressbar.visibility = View.VISIBLE
                loginBinding.loginProgressBar.animate()
            } else {
                loginBinding.overlayForProgressbar.visibility = View.GONE
                loginBinding.loginProgressBar.clearAnimation()
            }
        }
    }

    private fun renderLoadingState(isLoading: Boolean) {

    }

    private fun renderErrorState() {

    }
}
