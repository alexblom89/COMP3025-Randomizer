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

class SetItemListRVAdapter (
    val context: Context,
    val setItems : List<SetItem>,
    val itemListener : SetItemItemListener
) : RecyclerView.Adapter<SetItemListRVAdapter.SetItemViewHolder>() {

    private var selectedPos = -1

    inner class SetItemViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val setItemNameTextView = itemView.findViewById<TextView>(R.id.setItemNameTextView)
    }

    interface SetItemItemListener {
        fun setItemSelected(setItem: SetItem)
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

        holder.itemView.setOnClickListener {
            itemListener.setItemSelected(setItem)
            selectedPos = position
            notifyDataSetChanged()
        }

        holder.itemView.isSelected = selectedPos == position

    }

    override fun getItemCount(): Int {
        return setItems.size
    }

}