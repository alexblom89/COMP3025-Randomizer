package com.LH867295.comp3025assignment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class SetItemViewModelFactory(private val setID: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SetItemListViewModel::class.java))
        {
            return SetItemListViewModel(setID) as T
        }
        else
            throw IllegalArgumentException("Unknown ViewModel class")
    }
}