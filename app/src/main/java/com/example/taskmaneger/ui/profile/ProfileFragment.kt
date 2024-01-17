package com.example.taskmaneger.ui.profile

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import com.example.taskmaneger.data.local.Pref
import com.example.taskmaneger.databinding.FragmentProfileBinding
import com.example.taskmaneger.utils.loadImage

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val pref by lazy {
        Pref(requireContext())
    }
    private val binding get() = _binding!!

    private val getCommentMedia =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val selectedFileUri = result.data?.data
                pref.saveImage(selectedFileUri.toString())
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.userName.addTextChangedListener {
            pref.saveText(binding.userName.text.toString())
        }
        binding.userName.setText(pref.showText())
        binding.imgProfile.loadImage(pref.showImage())
        binding.imgProfile.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            getCommentMedia.launch(intent)
        }

         val launcher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK
                && result.data != null
            ) {
                val photoUri: Uri? = result.data?.data
                pref.setImage(photoUri.toString())
                binding.run {
                    pref.setImage(photoUri.toString())
                    imgProfile.run { loadImage(photoUri.toString()) }
                }
            }
        }

    }
}