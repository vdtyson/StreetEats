package com.versilistyson.androidstreeteats.presentation.ui.authentication.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment

import com.versilistyson.androidstreeteats.R

/**
 * A simple [Fragment] subclass.
 */
class CustomerSignupSuccessFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_customer_signup_success, container, false)
    }

}
