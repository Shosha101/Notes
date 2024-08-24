package com.example.notesapp.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.data.models.Note
import com.example.notesapp.databinding.ItemNoteBinding

class NotesAdapter(private val onNoteClick: (Note) -> Unit) : ListAdapter<Note, NotesAdapter.MyViewHolder>(UserItemDiffCallback()) {

    class MyViewHolder(val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemNoteBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val note = getItem(position)
        holder.binding.note = note

        holder.itemView.setOnClickListener {
            onNoteClick(note)
        }

        when (position % 5) {
            0-> holder.binding.card.setCardBackgroundColor(holder.binding.root.context.resources.getColor(
                R.color.blue
            ))
            1-> holder.binding.card.setCardBackgroundColor(holder.binding.root.context.resources.getColor(
                R.color.pink
            ))
            2-> holder.binding.card.setCardBackgroundColor(holder.binding.root.context.resources.getColor(
                R.color.orange
            ))
            3-> holder.binding.card.setCardBackgroundColor(holder.binding.root.context.resources.getColor(
                R.color.green
            ))
            4-> holder.binding.card.setCardBackgroundColor(holder.binding.root.context.resources.getColor(
                R.color.lightpink
            ))

        }
    }
}

class UserItemDiffCallback : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }
}