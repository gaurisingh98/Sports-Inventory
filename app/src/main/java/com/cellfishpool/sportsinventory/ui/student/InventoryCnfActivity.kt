package com.cellfishpool.sportsinventory.ui.student

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cellfishpool.sportsinventory.databinding.ActivityInventoryCnfBinding
import com.cellfishpool.sportsinventory.models.Student
import com.google.firebase.database.FirebaseDatabase


class InventoryCnfActivity : AppCompatActivity() {
    var database = FirebaseDatabase.getInstance()

    //    var sharedpreferences: SharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    private lateinit var sportsName: String
    private lateinit var binding: ActivityInventoryCnfBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInventoryCnfBinding.inflate(layoutInflater)
        sportsName = intent.getStringExtra("sportsName").toString()
        setContentView(binding.root)
        initToolbar()
        addListener()
    }

    private fun addListener() {
        with(binding) {
            btnConfirm.setOnClickListener {
                database.reference.child(etPhone.text.toString()).push()
                    .setValue(
                        Student(
                            etEmail.text.toString(),
                            sportsName, "pending", null, null
                        )
                    )
                //sharedpreferences.edit().putString("phoneno", etPhone.text.toString())
            }
            btnDismiss.setOnClickListener {
                startActivity(Intent(this@InventoryCnfActivity, StudentActivity::class.java))
            }
        }
    }

    private fun initToolbar() {
        with(binding.appBar.toolbar) {
            title = "Inventory Confirmation"
        }
    }
}