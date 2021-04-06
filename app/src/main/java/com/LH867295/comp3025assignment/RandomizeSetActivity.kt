package com.LH867295.comp3025assignment

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.LH867295.comp3025assignment.databinding.ActivityRandomizeSetBinding



class RandomizeSetActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRandomizeSetBinding

    private lateinit var viewModel : SetItemListViewModel
    private lateinit var viewModelFactory: SetItemViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRandomizeSetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val setID = intent.getStringExtra("setID")

        getRandomItem(setID)

        binding.randomizeAgainButton.setOnClickListener {
            getRandomItem(setID)
        }

        binding.chooseSetButton.setOnClickListener {
            val intent = Intent(this, SelectSetActivity::class.java)
            startActivity(intent)
        }
    }

    /**
     * This method gets the SetItems associated with the SetID from the Intent, and assigns
     * a random one to randomItem variable. The if statement checks if the assignment is the same as
     * the currently assigned item, and if so calls the function again until a new item has been
     * assigned.
     */
    private fun getRandomItem(id: String?) {
        id?.let {
            viewModelFactory = SetItemViewModelFactory(id)
            viewModel = ViewModelProvider(this, viewModelFactory).get(SetItemListViewModel::class.java)
            viewModel.getSetItems().observe(this, { items ->
                val randomItem = items.random()
                if(randomItem.name == binding.randomSetItemTextView.text)
                    getRandomItem(id)
                else
                    binding.randomSetItemTextView.text = randomItem.name
            })
        }
    }
}