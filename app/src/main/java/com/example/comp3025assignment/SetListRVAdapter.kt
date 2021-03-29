package com.example.comp3025assignment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.RecyclerView
import com.example.comp3025assignment.models.Set

class SetListRVAdapter(
    val context: Context,
    val sets: List<Set>,
    val itemListener: SetItemListener
) : RecyclerView.Adapter<SetListRVAdapter.SetViewHolder>()
{
    private val selectedPos = RecyclerView.NO_POSITION

    inner class SetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val setNameTextView = itemView.findViewById<TextView>(R.id.setNameTextView)
    }

    interface SetItemListener {
        fun setSelected(set: Set)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SetViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_set, parent, false)
        return SetViewHolder(view)
    }

    override fun onBindViewHolder(holder: SetViewHolder, position: Int) {
        val set = sets[position]

        with(holder) {
            setNameTextView.text = set.name
        }

        holder.itemView.setOnClickListener {
            itemListener.setSelected(set)
        }

        holder.itemView.isSelected = selectedPos == position

    }

    override fun getItemCount(): Int {
        return sets.size
    }

}
