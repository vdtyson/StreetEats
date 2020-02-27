package com.versilistyson.androidstreeteats.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.versilistyson.androidstreeteats.R
import com.versilistyson.androidstreeteats.StreetEatsApplication
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (applicationContext as StreetEatsApplication).appComponent.inject(this)
        viewModel = viewModelFactory.create(SharedViewModel::class.java)
    }
}
