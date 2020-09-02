package com.cellfishpool.sportsinventory.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cellfishpool.sportsinventory.databinding.LayoutSportsNameBinding
import com.cellfishpool.sportsinventory.ui.student.InventoryCnfActivity

class SportsListAdapter(private var sportsList: List<String>, private var context: Context) :
    RecyclerView.Adapter<SportsListAdapter.SportsListViewHolder>() {

    override fun getItemCount(): Int = sportsList.size

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
        holder.bind(sportsList[position])
    }


    inner class SportsListViewHolder(private var binding: LayoutSportsNameBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(sportsList: String) {
            with(binding) {
                tvSports.text = sportsList
                cardView.setOnClickListener {
                    val intent = Intent(context, InventoryCnfActivity::class.java)
                    intent.putExtra("sportsName", sportsList)
                    context.startActivity(intent)
                }
            }
        }
    }
}