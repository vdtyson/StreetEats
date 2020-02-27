package com.versilistyson.androidstreeteats.presentation.ui.authentication.login

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import com.versilistyson.androidstreeteats.R
import com.versilistyson.androidstreeteats.presentation.ui.MainActivity
import com.versilistyson.androidstreeteats.presentation.ui.SharedViewModel
import javax.inject.Inject


class LoginFragment : Fragment() {

    @Inject lateinit var loginViewModel: LoginViewModel
    private lateinit var viewModelProviderFactory: ViewModelProvider.Factory
    private lateinit var mainSharedViewModel: SharedViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activity = (activity as MainActivity)
        viewModelProviderFactory = activity.viewModelFactory
        mainSharedViewModel = ViewModelProvider(activity, viewModelProviderFactory).get(SharedViewModel::class.java)
        loginViewModel = viewModelProviderFactory.create(LoginViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in_screen, container, false)
    }


}
