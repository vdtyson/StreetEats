package com.versilistyson.androidstreeteats.presentation.ui.adapters

import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import com.versilistyson.androidstreeteats.R
import kotlinx.android.synthetic.main.activity_main.view.*

class BusinessSetupAdapter() : RecyclerView.Adapter<BusinessSetupAdapter.SetupCardHolder>() {

    class SetupCardHolder(view: View): RecyclerView.ViewHolder(view) {
        constructor(parent: ViewGroup) : this(LayoutInflater.from(parent.context).inflate(R.layout.fragment_authentication_main, parent, false))

    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BusinessSetupAdapter.SetupCardHolder =
        SetupCardHolder(parent)

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: BusinessSetupAdapter.SetupCardHolder, position: Int) {
        // TODO
    }


}