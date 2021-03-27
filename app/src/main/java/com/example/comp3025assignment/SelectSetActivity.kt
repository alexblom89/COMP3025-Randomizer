package com.example.comp3025assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.comp3025assignment.databinding.ActivitySelectSetBinding
import com.example.comp3025assignment.models.Set
import com.google.firebase.firestore.FirebaseFirestore

class SelectSetActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySelectSetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectSetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addNewSetButton.setOnClickListener {
            val intent = Intent(this, AddSetActivity::class.java)
            startActivity(intent)
        }
    }
}