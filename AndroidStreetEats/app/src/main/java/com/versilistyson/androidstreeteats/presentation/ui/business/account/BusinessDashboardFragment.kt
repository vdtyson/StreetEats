package com.versilistyson.androidstreeteats.presentation.ui.business.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.versilistyson.androidstreeteats.R
import com.versilistyson.androidstreeteats.databinding.FragmentBusinessDashboardBinding
import com.versilistyson.androidstreeteats.di.activityInjector
import com.versilistyson.androidstreeteats.di.util.DaggerViewModelFactory
import com.versilistyson.androidstreeteats.presentation.common.fragment.StartDestinationFragment
import com.versilistyson.androidstreeteats.presentation.ui.AppViewModel
import com.versilistyson.androidstreeteats.presentation.ui.UserState
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class BusinessDashboardFragment : StartDestinationFragment() {

    @Inject
    lateinit var daggerViewModelFactory: DaggerViewModelFactory
    private val viewModel: BusinessDashboardViewModel by viewModels {
        daggerViewModelFactory
    }

    lateinit var binding: FragmentBusinessDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        activityInjector.inject(this)
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBusinessDashboardBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getBusinessInfo()
        viewModel.state.observe(viewLifecycleOwner, Observer(::renderState))
    }

    private fun renderState(businessDashBoardState: BusinessDashBoardState) {
        when(businessDashBoardState) {
            is BusinessDashBoardState.AccountNotSetup -> {
                navController.navigate(R.id.action_businessDashboardFragment_to_businessSetupFragment)
            }
            is BusinessDashBoardState.Account -> return
        }
    }
}
