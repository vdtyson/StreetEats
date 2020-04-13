package com.versilistyson.androidstreeteats.presentation.ui.authentication.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.versilistyson.androidstreeteats.R
import com.versilistyson.androidstreeteats.data.firebase.models.AccountType
import com.versilistyson.androidstreeteats.databinding.FragmentSignupBinding
import com.versilistyson.androidstreeteats.di.activityInjector
import com.versilistyson.androidstreeteats.di.util.DaggerViewModelFactory
import com.versilistyson.androidstreeteats.presentation.ui.UserState
import com.versilistyson.androidstreeteats.presentation.ui.AppViewModel
import com.versilistyson.androidstreeteats.presentation.common.fragment.BaseFragment
import com.versilistyson.androidstreeteats.presentation.util.addOnBackPressedCallBack
import com.versilistyson.androidstreeteats.presentation.util.showToastMessage
import javax.inject.Inject


class SignupFragment : BaseFragment() {

    private lateinit var signupBinding: FragmentSignupBinding

    @Inject
    lateinit var daggerViewModelFactory: DaggerViewModelFactory

    private val appViewModel: AppViewModel by activityViewModels {
        daggerViewModelFactory
    }
    private val signUpViewModel: SignUpViewModel by viewModels {
        daggerViewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        signupBinding = FragmentSignupBinding.inflate(inflater, container, false)
        signupBinding.lifecycleOwner = this
        signupBinding.signUpViewModel = signUpViewModel
        return signupBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        activityInjector.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signUpViewModel.signUpState.observe(viewLifecycleOwner, Observer(::renderSignUpState))
        signUpViewModel.signupEvent.observe(viewLifecycleOwner, Observer(::handleSignUpEvent))
        appViewModel.userState.observe(viewLifecycleOwner, Observer(::renderUserState) )
    }

    override fun onStart() {
        super.onStart()
        addOnBackPressedCallBack(
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().navigate(SignupFragmentDirections.actionSignupFragmentToAuthenticationMainFragment())
                }
            }
        )

    }

    private fun renderSignUpState(signUpState: SignUpState) {
        signupBinding.signupProgressOverlay.progressIsVisible = signUpState.isLoading
        when (signUpState.accountType) {
            AccountType.CUSTOMER-> {
                this.showToastMessage("Customer")
            }
            AccountType.BUSINESS -> {
                this.showToastMessage("Business")
            }

        }
    }

    private fun renderUserState(userState: UserState) {
        when(userState) {
            is UserState.Authenticated -> {}
            UserState.Unauthenticated -> {}
        }
    }

    private fun handleSignUpEvent(signUpEvent: SignupEvent) {
        when (signUpEvent) {
            is SignupEvent.FailedSignup -> this.showToastMessage("Failed")
            is SignupEvent.SuccessfulSignUp -> this.showToastMessage("Success")
        }
    }
}
