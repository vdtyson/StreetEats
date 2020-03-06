package com.versilistyson.androidstreeteats.presentation.ui.authentication.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseUser

import com.versilistyson.androidstreeteats.databinding.FragmentLoginBinding
import com.versilistyson.androidstreeteats.di.activityInjector
import com.versilistyson.androidstreeteats.di.util.DaggerViewModelFactory
import com.versilistyson.androidstreeteats.domain.exception.Failure
import com.versilistyson.androidstreeteats.domain.exception.feature_failure.FireAuthFailure
import com.versilistyson.androidstreeteats.presentation.ui.AppState
import com.versilistyson.androidstreeteats.presentation.ui.MainSharedViewModel
import com.versilistyson.androidstreeteats.presentation.util.showToastMessage
import javax.inject.Inject


class LoginFragment : Fragment() {



    @Inject
    lateinit var daggerViewModelFactory: DaggerViewModelFactory

   private val loginViewModel: LoginViewModel by viewModels{
       daggerViewModelFactory
   }
    private val mainSharedViewModel: MainSharedViewModel by activityViewModels {
        daggerViewModelFactory
    }

    private lateinit var loginBinding: FragmentLoginBinding

    override fun onAttach(context: Context) {
        this.findNavController()
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        activityInjector.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loginBinding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        loginBinding.lifecycleOwner = this
        loginBinding.loginViewModel = loginViewModel
        return loginBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainSharedViewModel.appState.observe(viewLifecycleOwner, Observer(::renderSomething))
        loginViewModel.loginState.observe(viewLifecycleOwner, Observer(::renderLoginState))

    }


    private fun renderSomething(appState: AppState) {
        when(appState) {
            is AppState.SignedInUser ->
                this.showToastMessage("uid from main activity: ${appState.firebaseUser.uid}")
        }
    }
    private fun renderLoginState(loginState: LoginState) {
        when(loginState) {
            is LoginState.SuccessfulLogin -> {
                handleLoading(false)
            }
            is LoginState.FailedLogin -> {
                handleLoading(false)
                handleFailure(loginState.failure)
            }
            is LoginState.Loading -> {
                handleLoading(true)
            }
        }
    }
    private fun handleFailure(failure: Failure) {
        when(failure) {
            is FireAuthFailure.InvalidCredentials -> {
                this.showToastMessage("Invalid Credentials", Toast.LENGTH_SHORT)
            }
            else -> {
                this.showToastMessage(failure.exception.message ?: "", Toast.LENGTH_SHORT)
            }
        }
    }
    private fun handleLoading(isLoading: Boolean) {
        loginBinding.loginProgressOverlay.progressIsVisible = isLoading
    }
}
