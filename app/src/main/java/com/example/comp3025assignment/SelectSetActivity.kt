package com.example.comp3025assignment

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.comp3025assignment.databinding.ActivitySelectSetBinding
import com.example.comp3025assignment.models.Set

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

        binding.randomizeButton.setOnClickListener {
            Toast.makeText(this, "Please Select a Set", Toast.LENGTH_LONG).show()
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