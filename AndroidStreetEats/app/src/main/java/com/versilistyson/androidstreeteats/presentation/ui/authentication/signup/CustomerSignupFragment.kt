package com.versilistyson.androidstreeteats.presentation.ui.authentication.signup

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
import com.haroldadmin.vector.activityViewModel
import com.haroldadmin.vector.fragmentViewModel
import com.versilistyson.androidstreeteats.databinding.FragmentSignupBinding
import com.versilistyson.androidstreeteats.di.activityInjector
import com.versilistyson.androidstreeteats.di.app.component.AppComponent
import com.versilistyson.androidstreeteats.di.util.DaggerViewModelFactory
import com.versilistyson.androidstreeteats.domain.exception.Failure
import com.versilistyson.androidstreeteats.presentation.ui.AppState
import com.versilistyson.androidstreeteats.presentation.ui.MainSharedViewModel
import com.versilistyson.androidstreeteats.presentation.ui.common.BaseVectorFragment
import com.versilistyson.androidstreeteats.presentation.util.showToastMessage
import javax.inject.Inject


class CustomerSignupFragment : Fragment() {

    private lateinit var customerSignupBinding: FragmentSignupBinding

    @Inject
    lateinit var daggerViewModelFactory: DaggerViewModelFactory

    private val mainSharedViewModel: MainSharedViewModel by activityViewModels {
        daggerViewModelFactory
    }
    private val customerSignupViewModel: CustomerSignupViewModel by viewModels {
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
        customerSignupBinding = FragmentSignupBinding.inflate(inflater,container, false)
        customerSignupBinding.lifecycleOwner = this
        customerSignupBinding.customerSignupViewModel = customerSignupViewModel
        return customerSignupBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        activityInjector.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainSharedViewModel.appState.observe(viewLifecycleOwner, Observer(::renderSomething))
        customerSignupViewModel.customerSignupState.observe(viewLifecycleOwner, Observer(::renderSignupState))
    }

    private fun renderSomething(appState: AppState) {
        when(appState) {
            is AppState.SignedInUser ->
                this.showToastMessage("uid from main activity: ${appState.firebaseUser.uid}")
        }
    }
    private fun renderSignupState(customerSignupState: CustomerSignupState) {
        when(customerSignupState) {
            is CustomerSignupState.FailedSignup -> {
                handleLoading(false)
                showToastMessage(customerSignupState.failure.exception.message ?: "")
            }
            is CustomerSignupState.SuccessfulSignup -> {
                handleLoading(false)
                showToastMessage("Success signup")
            }
            is CustomerSignupState.Loading -> {
                handleLoading(true)
                showToastMessage("Loading")
            }
        }
    }

    private fun handleLoading(isLoading: Boolean) {
        customerSignupBinding.signupProgressOverlay.progressIsVisible = isLoading
    }

}
