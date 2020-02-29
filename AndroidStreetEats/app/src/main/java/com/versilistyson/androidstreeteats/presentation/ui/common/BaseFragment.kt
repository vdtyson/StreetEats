package com.versilistyson.androidstreeteats.presentation.ui.common

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.haroldadmin.vector.VectorFragment
import com.haroldadmin.vector.activityViewModel
import com.versilistyson.androidstreeteats.di.app.component.AppComponent
import com.versilistyson.androidstreeteats.presentation.ui.MainSharedViewModel

abstract class BaseFragment<out VM: BaseViewModel<*>>: VectorFragment() {

    protected lateinit var mainSharedViewModel: MainSharedViewModel
    abstract val viewModel: VM

    override fun onStart() {
        super.onStart()
        mainSharedViewModel = ViewModelProvider(requireActivity())[MainSharedViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


}