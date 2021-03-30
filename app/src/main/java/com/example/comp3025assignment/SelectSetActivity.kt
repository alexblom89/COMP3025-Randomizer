package com.example.comp3025assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StableIdKeyProvider
import androidx.recyclerview.selection.StorageStrategy
import com.example.comp3025assignment.databinding.ActivitySelectSetBinding
import com.example.comp3025assignment.models.Set
import com.google.firebase.firestore.FirebaseFirestore

class SelectSetActivity : AppCompatActivity(), SetListRVAdapter.SetItemListener {

    private lateinit var binding : ActivitySelectSetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectSetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val model : SetListViewModel by viewModels()
        model.getSets().observe(this, { sets->
            var recyclerAdapter = SetListRVAdapter(this, sets, this)
            binding.setRecyclerView.adapter = recyclerAdapter
        })

        binding.addNewSetButton.setOnClickListener {
            val intent = Intent(this, AddSetActivity::class.java)
            startActivity(intent)
        }
    }

    override fun setSelected(set: Set) {

        binding.editSetButton.setOnClickListener {
            val intent = Intent(this, EditSetActivity::class.java)
            intent.putExtra("setID", set.setID)
            intent.putExtra("setName", set.name)
            startActivity(intent)
        }

        binding.randomizeButton.setOnClickListener {
            val intent = Intent(this, RandomizeSetActivity::class.java)
            intent.putExtra("setID", set.setID)
            intent.putExtra("setName", set.name)
            startActivity(intent)
        }
    }
}