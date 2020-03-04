package com.versilistyson.androidstreeteats.presentation.ui.common

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.haroldadmin.vector.VectorFragment
import com.haroldadmin.vector.ViewModelOwner
import com.haroldadmin.vector.activityViewModel
import com.haroldadmin.vector.activityViewModelOwner
import com.versilistyson.androidstreeteats.di.app.component.AppComponent
import com.versilistyson.androidstreeteats.di.util.injector
import com.versilistyson.androidstreeteats.domain.exception.Failure
import com.versilistyson.androidstreeteats.presentation.ui.MainActivity
import com.versilistyson.androidstreeteats.presentation.ui.MainSharedViewModel
import javax.inject.Inject

abstract class BaseVectorFragment<VM : BaseViewModel<*>> : VectorFragment() {

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