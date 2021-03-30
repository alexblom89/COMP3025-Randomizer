package com.example.comp3025assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.comp3025assignment.databinding.ActivityEditSetBinding
import com.example.comp3025assignment.models.SetItem
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
            } else {
                Toast.makeText(this, "Set Item must have a name.", Toast.LENGTH_LONG).show()
            }
        }

        setID?.let {
            viewModelFactory = SetItemViewModelFactory(setID)

            viewModel = ViewModelProvider(this, viewModelFactory).get(SetItemListViewModel::class.java)
            viewModel.getSetItems().observe(this, { setItems ->
                var recyclerAdapter = SetItemListRVAdapter(this, setItems, this)
                binding.editSetRecyclerView.adapter = recyclerAdapter
            })
        }
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