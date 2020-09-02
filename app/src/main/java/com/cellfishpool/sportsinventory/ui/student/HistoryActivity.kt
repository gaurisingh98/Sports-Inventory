package com.cellfishpool.sportsinventory.ui.student

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.cellfishpool.sportsinventory.R
import com.cellfishpool.sportsinventory.adapters.InventoryStatusAdapter
import com.cellfishpool.sportsinventory.databinding.ActivityHistoryBinding
import com.cellfishpool.sportsinventory.models.Student
import com.cellfishpool.sportsinventory.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class HistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryBinding
    private var students: ArrayList<Student> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handleToolbar()
        handleRecyclerview()
    }

    private fun handleRecyclerview() {
        fetchData()
    }

    private fun fetchData() {
        val database = FirebaseDatabase.getInstance().getReference("9587873443")
        database.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                val iter = p0.children.iterator()
                while (iter.hasNext()) {
                    val snap = iter.next()
                    students.add(
                        Student(
                            snap.child("studentName").value.toString(),
                            snap.child("sportsName").value.toString(),
                            snap.child("status").value.toString(),
                            snap.child("time").value.toString(),
                            snap.child("venue").value.toString()
                        )
                    )
                }
                binding.rvInventoryStatus.let {
                    it.adapter = InventoryStatusAdapter(students, this@HistoryActivity)
                    it.layoutManager =
                        LinearLayoutManager(
                            this@HistoryActivity,
                            LinearLayoutManager.VERTICAL,
                            false
                        )
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

        }
        )
    }

    private fun handleToolbar() {
        with(binding.appBar.toolbar) {
            menu.clear()
            title = "History"
            inflateMenu(R.menu.menu_logout)
            setOnMenuItemClickListener {
                FirebaseAuth.getInstance().signOut()
                startActivity(Intent(this@HistoryActivity, LoginActivity::class.java))
                return@setOnMenuItemClickListener true
            }
        }
    }
}