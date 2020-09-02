package com.cellfishpool.sportsinventory.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cellfishpool.sportsinventory.databinding.LayoutInventoryStatusBinding
import com.cellfishpool.sportsinventory.models.Student

class InventoryStatusAdapter(
    private var eventList: List<Student>,
    private var context: Context,
    private var onclickEnabled: Boolean = false
) : RecyclerView.Adapter<InventoryStatusAdapter.InventoryStatusViewHolder>() {


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
        holder.bind(eventList[position])
    }

    override fun getItemCount(): Int = eventList.size

    inner class InventoryStatusViewHolder(private var binding: LayoutInventoryStatusBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(student: Student) {
            with(binding) {
                tvSports.text = student.sportsName
                tvStatus.text = student.status
                tvStudentName.text = student.studentName
                if (student.status == "pending" || student.status == "decline") {
                    tvTime.visibility = GONE
                    tvVenue.visibility = GONE
                } else {
                    tvTime.text = student.venue
                    tvVenue.text = student.time
                    tvTime.visibility = VISIBLE
                    tvVenue.visibility = VISIBLE
                }
            }
        }
    }
}