package com.versilistyson.androidstreeteats.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.haroldadmin.vector.activityViewModelOwner
import com.haroldadmin.vector.viewModel
import com.versilistyson.androidstreeteats.R
import com.versilistyson.androidstreeteats.di.StreetEatsApplication
import com.versilistyson.androidstreeteats.di.util.injector
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

   @Inject lateinit var mainSharedViewModelFactory: MainSharedViewModel.Factory

    val mainSharedViewModel: MainSharedViewModel by viewModel { initialState, handle ->
        mainSharedViewModelFactory.create(initialState)
    }

    private val host by lazy {
        NavHostFragment.create(R.navigation.nav_graph)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        mainSharedViewModelFactory = injector.mainSharedViewModelFactory
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host_fragment, host)
            .setPrimaryNavigationFragment(host).commit()
    }
}
