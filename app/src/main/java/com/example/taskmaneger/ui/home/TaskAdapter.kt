package com.example.taskmaneger.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmaneger.databinding.ItemTaskBinding
import com.example.taskmaneger.model.Task

class TaskAdapter(
    private val onLongClick:(position:Task)->Boolean
): RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    private val list = arrayListOf<Task>()
    fun addTasks(tasks: List<Task>) {
        list.clear()
        list.addAll(tasks)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(task: Task) = with(binding) {
            tvTitle.text = task.title
            tvDesc.text = task.desc
            itemView.setOnLongClickListener{onLongClick(task)}
        }
    }
}