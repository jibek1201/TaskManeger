package com.example.taskmaneger.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskmaneger.App
import com.example.taskmaneger.R
import com.example.taskmaneger.databinding.FragmentHomeBinding
import com.example.taskmaneger.model.Task

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val adapter = TaskAdapter(this::onLongClick)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        fab.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }
        val tasks = App.db.taskDao().getAll()
        adapter.addTasks(tasks)
        recyclerView.adapter = adapter
    }

    private fun onLongClick(position:Task):Boolean{
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
            .setTitle("Delete task")
            .setMessage("Do you want to delete ${position.title + " " + position.desc}?")
            .setPositiveButton("Yes"){ _, _ ->
                App.db.taskDao().delete(position)
                findNavController().navigate(R.id.navigation_home)
            }
            .setNegativeButton("Cancel"){ dialog, _ ->
                dialog.dismiss()
            }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

