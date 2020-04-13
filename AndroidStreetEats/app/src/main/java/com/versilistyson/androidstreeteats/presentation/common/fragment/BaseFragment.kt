package com.versilistyson.androidstreeteats.presentation.common.fragment

import android.content.Context
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.versilistyson.androidstreeteats.presentation.util.navigateUpOrFinish

open class BaseFragment() : Fragment() {

    protected lateinit var navController: NavController

    override fun onAttach(context: Context) {
        navController = findNavController()
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


}