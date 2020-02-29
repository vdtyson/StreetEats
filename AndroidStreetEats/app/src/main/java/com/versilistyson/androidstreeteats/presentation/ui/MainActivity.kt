package com.versilistyson.androidstreeteats.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.haroldadmin.vector.activityViewModelOwner
import com.haroldadmin.vector.viewModel
import com.versilistyson.androidstreeteats.R
import com.versilistyson.androidstreeteats.di.StreetEatsApplication
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

   @Inject lateinit var mainSharedViewModelFactory: MainSharedViewModel.Factory

    private val mainSharedViewModel: MainSharedViewModel by viewModel { initialState, handle ->
        mainSharedViewModelFactory.create(initialState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}
