package com.cellfishpool.sportsinventory.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cellfishpool.sportsinventory.databinding.LayoutSportsNameBinding

class SportsListAdapter : RecyclerView.Adapter<SportsListAdapter.SportsListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SportsListViewHolder {
        return SportsListViewHolder(
            LayoutSportsNameBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SportsListViewHolder, position: Int) {
    }

    override fun getItemCount(): Int = 5

    inner class SportsListViewHolder(private var binding: LayoutSportsNameBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }
}