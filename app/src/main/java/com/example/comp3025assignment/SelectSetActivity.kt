package com.example.comp3025assignment

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
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

        binding.editSetButton.setOnClickListener {
            Toast.makeText(this, "Please Select a Set", Toast.LENGTH_LONG).show()
        }
    }

    override fun setSelected(set: Set) {
        var viewModelFactory: SetItemViewModelFactory
        var viewModel: SetItemListViewModel
        val setID = set.setID

        binding.editSetButton.setOnClickListener {
            val intent = Intent(this, EditSetActivity::class.java)
            intent.putExtra("setID", set.setID)
            intent.putExtra("setName", set.name)
            startActivity(intent)
        }


        binding.randomizeButton.setOnClickListener {
            //Check if set has any items.
            setID?.let {

                viewModelFactory = SetItemViewModelFactory(setID)

                viewModel = ViewModelProvider(this, viewModelFactory).get(SetItemListViewModel::class.java)
                if (viewModel.getSetItems().value?.size == null)
                    Toast.makeText(this, "Set has no items!", Toast.LENGTH_LONG).show()
                else {
                    val intent = Intent(this, RandomizeSetActivity::class.java)
                    intent.putExtra("setID", setID)
                    intent.putExtra("setName", set.name)
                    startActivity(intent)
                }
            }
        }
    }
}