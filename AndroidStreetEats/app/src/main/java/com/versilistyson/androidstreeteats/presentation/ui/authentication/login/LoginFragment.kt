package com.versilistyson.androidstreeteats.presentation.ui.authentication.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.versilistyson.androidstreeteats.R

import com.versilistyson.androidstreeteats.databinding.FragmentLoginBinding
import com.versilistyson.androidstreeteats.di.activityInjector
import com.versilistyson.androidstreeteats.di.util.DaggerViewModelFactory
import com.versilistyson.androidstreeteats.domain.exception.Failure
import com.versilistyson.androidstreeteats.domain.exception.feature_failure.FireAuthFailure
import com.versilistyson.androidstreeteats.presentation.ui.UserState
import com.versilistyson.androidstreeteats.presentation.ui.AppViewModel
import com.versilistyson.androidstreeteats.presentation.common.fragment.BaseFragment
import com.versilistyson.androidstreeteats.presentation.util.addOnBackPressedCallBack
import com.versilistyson.androidstreeteats.presentation.util.showToastMessage
import javax.inject.Inject

object RequestCode {
    const val GOOGLE_SIGN_IN_RC = 248938290
}
class LoginFragment : BaseFragment() {


    @Inject
    lateinit var daggerViewModelFactory: DaggerViewModelFactory

    private val loginViewModel: LoginViewModel by viewModels {
        daggerViewModelFactory
    }

    private lateinit var loginBinding: FragmentLoginBinding

    override fun onAttach(context: Context) {
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

        /*appViewModel.userState.observe(viewLifecycleOwner, Observer(::renderUserState))*/
        loginViewModel.loginState.observe(viewLifecycleOwner, Observer(::renderLoginState))

    }

    override fun onStart() {
        super.onStart()
        addOnBackPressedCallBack(
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    navController.navigate(LoginFragmentDirections.actionLoginFragmentToAuthenticationMainFragment())
                }

            }
        )
    }

    /* private fun renderUserState(userState: UserState) {
        when (userState) {
            is UserState.Authenticated -> {
                showToastMessage("Success", Toast.LENGTH_LONG)
                showToastMessage(userState.userInfo.email)
                activityNavController.navigate(R.id.customer_nav_foodcart)
            }
        }
    }*/

    private fun renderLoginState(loginState: LoginState) {
        when (loginState) {
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
        when (failure) {
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
