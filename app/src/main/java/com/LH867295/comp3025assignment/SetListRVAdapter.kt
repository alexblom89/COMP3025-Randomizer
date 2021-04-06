package com.LH867295.comp3025assignment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.LH867295.comp3025assignment.models.Set

class SetListRVAdapter(
    val context: Context,
    val sets: List<Set>,
    val itemListener: SetItemListener
) : RecyclerView.Adapter<SetListRVAdapter.SetViewHolder>()
{
    private var selectedPos= -1

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

            //If item is already selected, de-select it.
            if(holder.itemView.isSelected) {
                selectedPos = -1
                notifyDataSetChanged()
            } else {
                selectedPos = position
                notifyDataSetChanged()
            }
        }

        holder.itemView.isSelected = selectedPos == position


    }

    override fun getItemCount(): Int {
        return sets.size
    }
}
