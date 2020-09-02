package com.cellfishpool.sportsinventory.ui.coach

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.cellfishpool.sportsinventory.R
import com.cellfishpool.sportsinventory.adapters.InventoryStatusAdapter
import com.cellfishpool.sportsinventory.databinding.ActivityCoachBinding
import com.cellfishpool.sportsinventory.models.Student
import com.cellfishpool.sportsinventory.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class CoachActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCoachBinding
    private var students: ArrayList<Student> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoachBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handleRecyclerview()
        handleToolbar()
    }

    private fun handleToolbar() {
        with(binding.appBar.toolbar) {
            menu.clear()
            title = "Inventory Requests"
            inflateMenu(R.menu.menu_logout)
            setOnMenuItemClickListener {
                FirebaseAuth.getInstance().signOut()
                startActivity(Intent(this@CoachActivity, LoginActivity::class.java))
                return@setOnMenuItemClickListener true
            }
        }
    }

    private fun handleRecyclerview() {
        val database = FirebaseDatabase.getInstance().reference
        database.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                val iter = p0.children.iterator()
                while (iter.hasNext()) {
                    val snap = iter.next().children.iterator()
                    while (snap.hasNext()) {
                        val sn = snap.next()
                        students.add(
                            Student(
                                sn.child("studentName").value.toString(),
                                sn.child("sportsName").value.toString(),
                                sn.child("status").value.toString(),
                                sn.child("time").value.toString(),
                                sn.child("venue").value.toString()
                            )
                        )
                    }
                }
                binding.rvInventoryStatus.let {
                    it.adapter = InventoryStatusAdapter(students, this@CoachActivity, true)
                    it.layoutManager =
                        LinearLayoutManager(
                            this@CoachActivity,
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

    override fun onDestroy() {
        FirebaseAuth.getInstance().signOut()
        super.onDestroy()
    }
}