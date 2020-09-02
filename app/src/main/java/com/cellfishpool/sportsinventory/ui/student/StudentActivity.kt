package com.cellfishpool.sportsinventory.ui.student

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.cellfishpool.sportsinventory.R
import com.cellfishpool.sportsinventory.adapters.SportsListAdapter
import com.cellfishpool.sportsinventory.databinding.ActivityStudentBinding

class StudentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStudentBinding
    private val sportsList =
        listOf<String>("Cricket", "Badminton", "Volleyball", "Table Tennis", "Lawn Tennis")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handleToolbar()
        handleRecyclerview()
    }

    private fun handleRecyclerview() {
        binding.rvSportsName.let {
            it.adapter = SportsListAdapter(sportsList, this)
            it.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun handleToolbar() {
        with(binding.appBar.toolbar) {
            menu.clear()
            title = "AVAILABLE SPORTS"
            inflateMenu(R.menu.menu_toolbar)
            setOnMenuItemClickListener {
                startActivity(Intent(this@StudentActivity, HistoryActivity::class.java))
                return@setOnMenuItemClickListener true
            }
        }
    }
}