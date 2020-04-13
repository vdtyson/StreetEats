package com.versilistyson.androidstreeteats.presentation.ui.customer.foodvendors

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.versilistyson.androidstreeteats.R

/**
 * A simple [Fragment] subclass.
 */
class NearbyVendorsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nearby_vendors, container, false)
    }

}
