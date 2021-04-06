package com.LH867295.comp3025assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    }
}