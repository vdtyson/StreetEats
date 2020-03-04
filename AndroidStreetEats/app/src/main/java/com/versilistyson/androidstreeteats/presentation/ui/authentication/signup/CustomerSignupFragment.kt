package com.versilistyson.androidstreeteats.presentation.ui.authentication.signup

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.haroldadmin.vector.fragmentViewModel
import com.versilistyson.androidstreeteats.databinding.FragmentSignupBinding

import com.versilistyson.androidstreeteats.di.util.injector
import com.versilistyson.androidstreeteats.domain.exception.Failure
import com.versilistyson.androidstreeteats.presentation.ui.common.BaseVectorFragment
import javax.inject.Inject


class CustomerSignupFragment : BaseVectorFragment<CustomerSignupViewModel>() {

    @Inject
    lateinit var customerSignupViewModelFactory: CustomerSignupViewModel.Factory
    lateinit var customerSignupBinding: FragmentSignupBinding

    override val viewModel: CustomerSignupViewModel by fragmentViewModel { initialState, handle ->
        customerSignupViewModelFactory.create(initialState)
    }

    override fun onAttach(context: Context) {
        customerSignupViewModelFactory = requireActivity().injector.customerSignupViewModelFactory
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        customerSignupBinding = FragmentSignupBinding.inflate(inflater,container, false)
        customerSignupBinding.lifecycleOwner = this
        return customerSignupBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun renderLoadingState(isLoading: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun renderErrorState(showError: Boolean, errorMessage: String?, failure: Failure?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
