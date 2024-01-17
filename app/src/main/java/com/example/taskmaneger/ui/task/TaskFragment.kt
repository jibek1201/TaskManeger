package com.example.taskmaneger.ui.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.taskmaneger.App
import com.example.taskmaneger.R
import com.example.taskmaneger.databinding.FragmentTaskBinding
import com.example.taskmaneger.model.Task
import com.example.taskmaneger.ui.home.HomeFragment

class TaskFragment : Fragment() {
    private lateinit var binding: FragmentTaskBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val position = arguments?.getSerializable(HomeFragment.TASK_UPDATE) as Task?
        binding.etTitle.setText(position?.title)
        binding.etDesc.setText(position?.desc)
        if (position != null) {
            binding.btnSave.text = getString(R.string.update)
            binding.btnSave.setOnClickListener {
                val updateTask = position.copy(
                    title = binding.etTitle.text.toString(),
                    desc = binding.etDesc.text.toString()
                )
                App.db.taskDao().update(updateTask)
                findNavController().navigateUp()
            }
        } else {
            binding.btnSave.setOnClickListener {
                val task = Task(
                    title = binding.etTitle.text.toString(),
                    desc = binding.etDesc.text.toString()
                )
                App.db.taskDao().insert(task)
                findNavController().navigateUp()
            }
        }
    }
}
