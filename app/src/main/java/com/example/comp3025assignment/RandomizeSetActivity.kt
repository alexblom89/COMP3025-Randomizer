package com.example.comp3025assignment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.comp3025assignment.databinding.ActivityRandomizeSetBinding
import com.example.comp3025assignment.models.SetItem
import java.util.*


class RandomizeSetActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRandomizeSetBinding

    private lateinit var viewModel : SetItemListViewModel
    private lateinit var viewModelFactory: SetItemViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRandomizeSetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val setID = intent.getStringExtra("setID")

        setID?.let {
            viewModelFactory = SetItemViewModelFactory(setID)
            viewModel = ViewModelProvider(this, viewModelFactory).get(SetItemListViewModel::class.java)
            viewModel.getSetItems().observe(this, { items ->
                val randomItem = items.random()
                binding.randomSetItemTextView.text = randomItem.name
            })
        }
    }
    //TODO: Hook up buttons.
}