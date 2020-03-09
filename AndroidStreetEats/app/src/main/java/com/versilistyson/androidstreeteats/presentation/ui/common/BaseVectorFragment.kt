package com.versilistyson.androidstreeteats.presentation.ui.common

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.haroldadmin.vector.VectorFragment
import com.versilistyson.androidstreeteats.di.app.component.AppComponent
import com.versilistyson.androidstreeteats.domain.exception.Failure

abstract class BaseVectorFragment<VM : BaseViewModel<*>> : VectorFragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    abstract val viewModel: VM
    protected val navController: NavController by lazy {
        findNavController()
    }

    abstract fun renderLoadingState(isLoading: Boolean)
    abstract fun renderErrorState(showError: Boolean, errorMessage: String? = null, failure: Failure? = null)

}

abstract class BaseFragment : Fragment() {

    protected val navController: NavController by lazy {
        findNavController()
    }

}