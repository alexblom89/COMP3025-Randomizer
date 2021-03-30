package com.example.comp3025assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.comp3025assignment.databinding.ActivityRandomizeSetBinding


class RandomizeSetActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRandomizeSetBinding

    private lateinit var viewModel : SetItemListViewModel
    private lateinit var viewModelFactory: SetItemViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRandomizeSetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val setID = intent.getStringExtra("setID")
    }


}