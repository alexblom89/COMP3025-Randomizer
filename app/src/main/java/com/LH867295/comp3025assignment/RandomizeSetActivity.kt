package com.LH867295.comp3025assignment

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.LH867295.comp3025assignment.databinding.ActivityRandomizeSetBinding
import com.LH867295.comp3025assignment.models.Set


class RandomizeSetActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRandomizeSetBinding
    val setID = intent.getStringExtra("setID")
    val setName = intent.getStringExtra("name")

    private lateinit var viewModel : SetItemListViewModel
    private lateinit var viewModelFactory: SetItemViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRandomizeSetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getRandomItem(setID)

        binding.randomizeAgainButton.setOnClickListener {
            getRandomItem(setID)
        }

        binding.chooseSetButton.setOnClickListener {
            val intent = Intent(this, SelectSetActivity::class.java)
            startActivity(intent)
        }
        setSupportActionBar(binding.mainToolbar.toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_add_set -> {
                startActivity(Intent(applicationContext, AddSetActivity::class.java))
                return true
            }
            R.id.action_edit_set -> {
                val intent = Intent(applicationContext, EditSetActivity::class.java)
                intent.putExtra("setID", setID)
                intent.putExtra("name", setName)
                startActivity(intent)
//                Toast.makeText(applicationContext, "Please Select a Set", Toast.LENGTH_LONG).show()
                return true
            }
            R.id.action_list -> {
                startActivity(Intent(applicationContext, SelectSetActivity::class.java))
                Toast.makeText(applicationContext, "You're Already Here!", Toast.LENGTH_LONG).show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
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