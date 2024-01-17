package com.example.taskmaneger.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.taskmaneger.App.Companion.db
import com.example.taskmaneger.R
import com.example.taskmaneger.databinding.FragmentDashboardBinding
import com.example.taskmaneger.model.Film
import com.example.taskmaneger.utils.showToast
import com.google.firebase.auth.FirebaseAuth

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSave.setOnClickListener {
            val data = Film(binding.etDas.text.toString(), binding.etDask.text.toString())
//            FirebaseAuth.getInstance().currentUser?.uid?.let { it1 ->
//                db.collection(it1)
//                    .add(data)
//                    .addOnSuccessListener {
//                        showToast(getString(R.string.success_msg))
//                    }
//                    .addOnFailureListener {
//                        showToast(it.message.toString())
//                    }
//            }
        }
    }
}