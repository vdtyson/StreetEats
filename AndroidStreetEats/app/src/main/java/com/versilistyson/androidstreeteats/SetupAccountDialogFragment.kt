package com.versilistyson.androidstreeteats

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.versilistyson.androidstreeteats.databinding.FragmentSetupAccountDialogBinding


class SetupAccountDialogFragment : DialogFragment() {

    lateinit var setupAccountDialogBinding: FragmentSetupAccountDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setupAccountDialogBinding = FragmentSetupAccountDialogBinding.inflate(inflater, container, false)
        setupAccountDialogBinding.lifecycleOwner = this
        return setupAccountDialogBinding.root
    }

}
