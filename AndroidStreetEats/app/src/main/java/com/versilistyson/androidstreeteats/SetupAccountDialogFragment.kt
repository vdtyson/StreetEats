package com.versilistyson.androidstreeteats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.DialogFragment
import com.versilistyson.androidstreeteats.databinding.FragmentSetupAccountDialogBinding
import com.versilistyson.androidstreeteats.presentation.util.addOnBackPressedCallBack


class SetupAccountDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentSetupAccountDialogBinding
    private val finishActivityOnBackPressedCallback: OnBackPressedCallback =
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSetupAccountDialogBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupButtons()
    }

    override fun onStart() {
        super.onStart()
        addOnBackPressedCallBack(finishActivityOnBackPressedCallback)
    }

    private fun setupButtons() {
        binding.bttnExit.setOnClickListener {
            requireActivity().finish()
        }
        binding.bttnSetupAccount.setOnClickListener {
            TODO("Go to setup page")
        }
    }


}
