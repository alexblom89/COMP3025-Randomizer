package com.LH867295.comp3025assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.LH867295.comp3025assignment.databinding.ActivityAddSetBinding
import com.LH867295.comp3025assignment.models.Set
import com.google.firebase.firestore.FirebaseFirestore


class AddSetActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAddSetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddSetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveNewSetButton.setOnClickListener {
            if (binding.newSetNameEditText.text.toString().isNotEmpty())
            {
                val set = Set()
                set.name = binding.newSetNameEditText.text.toString()

                val db = FirebaseFirestore.getInstance().collection("sets")
                set.setID = db.document().id

                db.document(set.setID!!).set(set)
                        .addOnSuccessListener {
                            Toast.makeText(this, "Set Added!", Toast.LENGTH_LONG).show()
                            binding.newSetNameEditText.setText("")
                        }
                        .addOnFailureListener {
                            Toast.makeText(this, it.localizedMessage, Toast.LENGTH_LONG).show()
                            binding.newSetNameEditText.setText("")
                        }
            } else {
                Toast.makeText(this, "Set needs a name!", Toast.LENGTH_LONG).show()
            }
        }

        binding.backToSelectSetViewFAB.setOnClickListener {
            finish()
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
//                Toast.makeText(applicationContext, "Please Select a Set", Toast.LENGTH_LONG).show()
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
}