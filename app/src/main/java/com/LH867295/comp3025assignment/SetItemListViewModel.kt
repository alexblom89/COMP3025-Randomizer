package com.LH867295.comp3025assignment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.LH867295.comp3025assignment.models.SetItem
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class SetItemListViewModel(setID: String) : ViewModel() {
    private val setItems : MutableLiveData<List<SetItem>> by lazy {
        MutableLiveData()
    }

    init {
        val db = FirebaseFirestore.getInstance().collection("setItems")
                .whereEqualTo("setID", setID)
                .orderBy("name", Query.Direction.ASCENDING)

        db.addSnapshotListener { documents, exception ->
            if (exception != null) {
                Log.w("DBQUERY", "Listen Failed")
                return@addSnapshotListener
            }
            documents?.let {
                val setItemList = ArrayList<SetItem>()
                for (document in documents)
                {
                    val item = document.toObject(SetItem::class.java)
                    setItemList.add(item)
                }
                setItems.value = setItemList
            }
        }
    }

    fun getSetItems() : LiveData<List<SetItem>> {
        return setItems
    }
}