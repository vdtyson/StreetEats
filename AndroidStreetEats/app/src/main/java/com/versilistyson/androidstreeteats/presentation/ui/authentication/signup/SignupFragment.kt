package com.versilistyson.androidstreeteats.presentation.ui.authentication.signup

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.versilistyson.androidstreeteats.data.firebase.models.AccountType
import com.versilistyson.androidstreeteats.databinding.FragmentSignupBinding
import com.versilistyson.androidstreeteats.di.activityInjector
import com.versilistyson.androidstreeteats.di.util.DaggerViewModelFactory
import com.versilistyson.androidstreeteats.presentation.ui.UserState
import com.versilistyson.androidstreeteats.presentation.ui.MainSharedViewModel
import com.versilistyson.androidstreeteats.presentation.util.showToastMessage
import javax.inject.Inject


class SignupFragment : Fragment() {

    private lateinit var signupBinding: FragmentSignupBinding

    @Inject
    lateinit var daggerViewModelFactory: DaggerViewModelFactory

    private val mainSharedViewModel: MainSharedViewModel by activityViewModels {
        daggerViewModelFactory
    }
    private val signUpViewModel: SignUpViewModel by viewModels {
        daggerViewModelFactory
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
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

    private fun handleSignUpEvent(signUpEvent: SignupEvent) {
        when (signUpEvent) {
            is SignupEvent.FailedSignup -> this.showToastMessage("Failed")
            is SignupEvent.SuccessfulSignUp -> this.showToastMessage("Success")
        }
    }
}
