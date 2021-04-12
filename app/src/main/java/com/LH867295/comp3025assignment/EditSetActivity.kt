package com.LH867295.comp3025assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.LH867295.comp3025assignment.databinding.ActivityEditSetBinding
import com.LH867295.comp3025assignment.models.SetItem
import com.google.firebase.firestore.FirebaseFirestore

class EditSetActivity : AppCompatActivity(), SetItemListRVAdapter.SetItemItemListener {
    private lateinit var binding : ActivityEditSetBinding

    private lateinit var viewModel: SetItemListViewModel
    private lateinit var viewModelFactory: SetItemViewModelFactory

    private val db = FirebaseFirestore.getInstance().collection("setItems")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditSetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.editSetNameTextView.text = intent.getStringExtra("setName")
        val setID = intent.getStringExtra("setID")

        binding.addItemButton.setOnClickListener {
            if (binding.addNewSetItemEditText.text.toString().isNotEmpty())
            {
                //val db = FirebaseFirestore.getInstance().collection("setItems")
                val id = db.document().id

                val newSetItem = SetItem(id, binding.addNewSetItemEditText.text.toString(), setID)
                db.document(id).set(newSetItem)
                    .addOnSuccessListener { Toast.makeText(this, "Set Item Added", Toast.LENGTH_LONG).show() }
                    .addOnFailureListener { Toast.makeText(this, "Failed To Add Set Item!", Toast.LENGTH_LONG).show() }
                binding.addNewSetItemEditText.setText("")
            } else {
                Toast.makeText(this, "Set Item must have a name.", Toast.LENGTH_LONG).show()
            }
        }

        //Set the add and delete buttons to be the same width.
        //binding.addItemButton.width = binding.deleteItemButton.width

        binding.deleteItemButton.setOnClickListener {
            Toast.makeText(this, "Please Select An Item To Delete", Toast.LENGTH_LONG).show()
        }

        binding.backToSelectFAB.setOnClickListener {
            finish()
        }

        setID?.let {
            viewModelFactory = SetItemViewModelFactory(setID)

            viewModel = ViewModelProvider(this, viewModelFactory).get(SetItemListViewModel::class.java)
            viewModel.getSetItems().observe(this, { setItems ->
                var recyclerAdapter = SetItemListRVAdapter(this, setItems, this)
                binding.editSetRecyclerView.adapter = recyclerAdapter
            })
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
//                startActivity(Intent(applicationContext, EditSetActivity::class.java))
                Toast.makeText(applicationContext, "You're Already Here!", Toast.LENGTH_LONG).show()
                return true
            }
            R.id.action_list -> {
                startActivity(Intent(applicationContext, SelectSetActivity::class.java))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun setItemSelected(setItem: SetItem) {
        binding.deleteItemButton.setOnClickListener {
            setItem.setItemID?.let {
                it1 -> db.document(it1)
                .delete()
                .addOnSuccessListener { Toast.makeText(this, "Set Item Deleted", Toast.LENGTH_LONG).show() }
                .addOnFailureListener { Toast.makeText(this, "Set Item Could Not Be Deleted", Toast.LENGTH_LONG).show() }
            }
        }
    }
}