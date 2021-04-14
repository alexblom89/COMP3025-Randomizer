package com.LH867295.comp3025assignment

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.LH867295.comp3025assignment.databinding.ActivitySelectSetBinding
import com.LH867295.comp3025assignment.models.Set

class SelectSetActivity : AppCompatActivity(), SetListRVAdapter.SetItemListener {

    private lateinit var binding : ActivitySelectSetBinding
    private var setSelected : Set? = null

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
                if (setSelected == null)
                    Toast.makeText(applicationContext, "Please Select a Set", Toast.LENGTH_LONG).show()
                else {
                    val intent = Intent(applicationContext, EditSetActivity::class.java)
                    intent.putExtra("setID", setSelected!!.setID)
                    intent.putExtra("name", setSelected!!.name)
                    startActivity(intent)
                }
                return true
            }
            R.id.action_list -> {
//                startActivity(Intent(applicationContext, SelectSetActivity::class.java))
                Toast.makeText(applicationContext, "You're Already Here!", Toast.LENGTH_LONG).show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun setSelected(set: Set) {
        setSelected = set
        var viewModelFactory: SetItemViewModelFactory
        var viewModel: SetItemListViewModel
        val setID = set.setID

        binding.editSetButton.setOnClickListener {
            val intent = Intent(this, EditSetActivity::class.java)
            intent.putExtra("setID", set.setID)
            intent.putExtra("name", set.name)
            startActivity(intent)
        }

        binding.randomizeButton.setOnClickListener {
            //Check if set has any items.
            setID?.let {

                viewModelFactory = SetItemViewModelFactory(setID)
                viewModel = ViewModelProvider(this, viewModelFactory).get(SetItemListViewModel::class.java)
                val listSize = viewModel.getSetItems().value

                if (listSize == null)
                    Toast.makeText(this, "Set has no items!", Toast.LENGTH_LONG).show()
                else {
                    val intent = Intent(this, RandomizeSetActivity::class.java)
                    intent.putExtra("setID", setID)
                    intent.putExtra("name", set.name)
                    startActivity(intent)
                }
            }
        }
    }
}