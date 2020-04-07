package com.versilistyson.androidstreeteats.presentation.common.fragment

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import com.versilistyson.androidstreeteats.presentation.util.addOnBackPressedCallBack
import javax.inject.Singleton

open class StartDestinationFragment: BaseFragment() {

    protected open val finishActivityOnBackPressedCallback: OnBackPressedCallback =
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }
        }

    override fun onStart() {
        super.onStart()

        // onBackPressed closes app
        this.addOnBackPressedCallBack(finishActivityOnBackPressedCallback)
    }
}