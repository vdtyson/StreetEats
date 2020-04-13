package com.versilistyson.androidstreeteats.presentation.ui.business.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.versilistyson.androidstreeteats.databinding.FragmentBusinessSettingsBinding
import com.versilistyson.androidstreeteats.di.activityInjector
import com.versilistyson.androidstreeteats.di.util.DaggerViewModelFactory
import com.versilistyson.androidstreeteats.presentation.common.fragment.BaseFragment
import com.versilistyson.androidstreeteats.presentation.common.fragment.StartDestinationFragment
import javax.inject.Inject

class BusinessSettingsFragment : StartDestinationFragment() {

    @Inject
    lateinit var daggerViewModelFactory: DaggerViewModelFactory

    private val businessSettingsViewModel: BusinessSettingsViewModel by viewModels {
        daggerViewModelFactory
    }

    lateinit var businessSettingsBinding: FragmentBusinessSettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        activityInjector.inject(this)
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        businessSettingsBinding = FragmentBusinessSettingsBinding.inflate(inflater, container, false)
        businessSettingsBinding.lifecycleOwner = this
        return businessSettingsBinding.root
    }
}
