package com.versilistyson.androidstreeteats.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.haroldadmin.vector.viewModel
import com.versilistyson.androidstreeteats.R
import com.versilistyson.androidstreeteats.di.injector
import com.versilistyson.androidstreeteats.di.util.DaggerViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: DaggerViewModelFactory

    private val mainSharedViewModel: MainSharedViewModel by viewModels{
        viewModelFactory
    }
    private val host by lazy {
        NavHostFragment.create(R.navigation.nav_graph)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host_fragment, host)
            .setPrimaryNavigationFragment(host).commit()
    }
}
