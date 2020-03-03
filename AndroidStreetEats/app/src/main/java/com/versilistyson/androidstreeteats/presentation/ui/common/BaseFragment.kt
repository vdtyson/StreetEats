package com.versilistyson.androidstreeteats.presentation.ui.common

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.haroldadmin.vector.VectorFragment
import com.haroldadmin.vector.ViewModelOwner
import com.haroldadmin.vector.activityViewModel
import com.haroldadmin.vector.activityViewModelOwner
import com.versilistyson.androidstreeteats.di.app.component.AppComponent
import com.versilistyson.androidstreeteats.di.util.injector
import com.versilistyson.androidstreeteats.presentation.ui.MainActivity
import com.versilistyson.androidstreeteats.presentation.ui.MainSharedViewModel
import javax.inject.Inject

abstract class BaseFragment<out VM: BaseViewModel<*>>: VectorFragment() {

    abstract val viewModel: VM

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


}