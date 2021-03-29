package com.example.comp3025assignment

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.comp3025assignment.models.SetItem

class SetItemListRVAdapter (val context: Context,
                            val setItems : List<SetItem>) : RecyclerView.Adapter<SetItemListRVAdapter.SetItemViewHolder>() {

    inner class SetItemViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val setItemNameTextView = itemView.findViewById<TextView>(R.id.setItemNameTextView)
    }

    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int): SetItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_set_item, parent, false)
        return SetItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: SetItemViewHolder, position: Int) {
        val setItem = setItems[position]
        with (holder) {
            setItemNameTextView.text = setItem.name
        }
    }

    override fun getItemCount(): Int {
        return setItems.size
    }

}