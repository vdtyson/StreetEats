package com.versilistyson.androidstreeteats.presentation.ui.business.track

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.versilistyson.androidstreeteats.R
import com.versilistyson.androidstreeteats.presentation.common.fragment.StartDestinationFragment

/**
 * A simple [Fragment] subclass.
 */
class BusinessTrackFragment : StartDestinationFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_business_track, container, false)
    }

}
