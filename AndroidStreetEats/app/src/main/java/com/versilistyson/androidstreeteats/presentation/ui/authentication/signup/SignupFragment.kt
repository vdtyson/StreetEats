package com.versilistyson.androidstreeteats.presentation.ui.authentication.signup

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.haroldadmin.vector.fragmentViewModel

import com.versilistyson.androidstreeteats.R
import com.versilistyson.androidstreeteats.di.util.injector
import com.versilistyson.androidstreeteats.presentation.ui.common.BaseFragment
import javax.inject.Inject


class SignupFragment : BaseFragment<SignupViewModel>() {
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
