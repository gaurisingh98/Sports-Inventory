package com.cellfishpool.sportsinventory.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cellfishpool.sportsinventory.databinding.LayoutInventoryStatusBinding
import com.cellfishpool.sportsinventory.utils.OnItemClickListner

class InventoryStatusStudentAdapter(
    private var eventList: List<String>,
    private val itemClickListener: OnItemClickListner
) : RecyclerView.Adapter<InventoryStatusStudentAdapter.InventoryStatusViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InventoryStatusViewHolder {
        return InventoryStatusViewHolder(
            LayoutInventoryStatusBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: InventoryStatusViewHolder, position: Int) {
    }

    override fun getItemCount(): Int = 5

    inner class InventoryStatusViewHolder(private var binding: LayoutInventoryStatusBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }
}