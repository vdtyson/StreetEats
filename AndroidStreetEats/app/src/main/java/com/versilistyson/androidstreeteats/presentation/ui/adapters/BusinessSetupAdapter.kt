package com.versilistyson.androidstreeteats.presentation.ui.adapters

import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater

class BusinessSetupAdapter() : RecyclerView.Adapter<BusinessSetupAdapter.SetupCardHolder>() {

    inner class SetupCardHolder(view: View): RecyclerView.ViewHolder(view) {
        // TODO: Inflate layout
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