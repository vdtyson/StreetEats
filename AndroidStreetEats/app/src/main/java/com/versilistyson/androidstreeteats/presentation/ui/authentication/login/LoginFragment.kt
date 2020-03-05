package com.versilistyson.androidstreeteats.presentation.ui.authentication.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseUser
import com.haroldadmin.vector.*

import com.versilistyson.androidstreeteats.databinding.FragmentLoginBinding
import com.versilistyson.androidstreeteats.di.activityInjector
import com.versilistyson.androidstreeteats.di.util.DaggerViewModelFactory
import com.versilistyson.androidstreeteats.domain.exception.Failure
import com.versilistyson.androidstreeteats.presentation.ui.MainSharedViewModel
import com.versilistyson.androidstreeteats.presentation.ui.common.BaseVectorFragment
import com.versilistyson.androidstreeteats.presentation.util.showToastMessage
import java.util.*
import javax.inject.Inject


class LoginFragment : BaseVectorFragment<LoginViewModel>() {


    @Inject
    lateinit var loginViewModelFactory: LoginViewModel.Factory

    override val viewModel: LoginViewModel by fragmentViewModel { initialState, handle ->
        loginViewModelFactory.create(initialState)
    }

    @Inject
    lateinit var daggerViewModelFactory: DaggerViewModelFactory
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
        loginBinding.loginViewModel = viewModel
        return loginBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userObserver = Observer<FirebaseUser>{ firebaseUser ->
            renderToastFromMainActivity(firebaseUser)
        }
        mainSharedViewModel.firebaseUser.observe(viewLifecycleOwner, userObserver)
        renderState(viewModel) { state ->
            renderLoadingState(state.isLoading)
            renderErrorState(state.showError, state.errorMessage, state.failure)
            renderLoginSuccessState(state.isLoginSuccessful)
        }
    }

    override fun renderLoadingState(isLoading: Boolean) {
        if (isLoading) {
            loginBinding.bttnSignIn.isClickable = false
            loginBinding.overlayForProgressbar.visibility = View.VISIBLE
            loginBinding.loginProgressBar.animate()
        } else {
            loginBinding.bttnSignIn.isClickable = true
            loginBinding.overlayForProgressbar.visibility = View.GONE
            loginBinding.loginProgressBar.clearAnimation()
        }
    }

    override fun renderErrorState(showError: Boolean, errorMessage: String?, failure: Failure?) {
        if (showError) {
            this.showToastMessage(errorMessage ?: "Error", Toast.LENGTH_SHORT)
        }
    }

    private fun renderLoginSuccessState(isLoginSuccessful: Boolean) {
        if (isLoginSuccessful) {
            mainSharedViewModel.getFirebaseUser()
        }
    }

    private fun renderToastFromMainActivity(firebaseUser: FirebaseUser) {
            this.showToastMessage("uid from mainActivity: ${firebaseUser.uid}", Toast.LENGTH_LONG)
    }
}
