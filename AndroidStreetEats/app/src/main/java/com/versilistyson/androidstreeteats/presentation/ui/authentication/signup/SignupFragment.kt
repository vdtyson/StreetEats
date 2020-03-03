package com.versilistyson.androidstreeteats.presentation.ui.authentication.signup

import android.content.Context
import android.os.Bundle
import com.haroldadmin.vector.fragmentViewModel

import com.versilistyson.androidstreeteats.di.util.injector
import com.versilistyson.androidstreeteats.presentation.ui.common.BaseVectorFragment
import javax.inject.Inject


class SignupFragment : BaseVectorFragment<SignupViewModel>() {

    @Inject
    lateinit var signupViewModelFactory: SignupViewModel.Factory

    override val viewModel: SignupViewModel by fragmentViewModel { initialState, handle ->
        signupViewModelFactory.create(initialState)
    }

    override fun onAttach(context: Context) {
        signupViewModelFactory = requireActivity().injector.signupViewModelFactory
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


}
