package com.LH867295.comp3025assignment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.LH867295.comp3025assignment.models.Set
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class SetListViewModel : ViewModel() {

    private val sets : MutableLiveData<List<Set>> by lazy {
        MutableLiveData()
    }

    init {
        loadSets()
    }

    fun getSets() : LiveData<List<Set>> {
        return sets
    }

    private fun loadSets() {
        val db = FirebaseFirestore.getInstance().collection("sets")
            .orderBy("name", Query.Direction.ASCENDING)

        db.addSnapshotListener { documents, exception ->
            if(exception != null) {
                Log.w("DB_RESPONSE", "Listen failed", exception)
                return@addSnapshotListener
            }

            val setList = ArrayList<Set>()

            documents?.let {
                for(document in documents) {
                    val set = document.toObject(Set::class.java)
                    setList.add(set)
                }
                sets.value = setList
            }
        }
    }
}