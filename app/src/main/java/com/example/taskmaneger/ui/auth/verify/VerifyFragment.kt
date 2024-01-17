package com.example.taskmaneger.ui.auth.verify

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.taskmaneger.R
import com.example.taskmaneger.databinding.FragmentVerifyBinding
import com.example.taskmaneger.ui.auth.phone.PhoneFragment
import com.example.taskmaneger.utils.showToast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

class VerifyFragment : Fragment() {

    private lateinit var binding: FragmentVerifyBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVerifyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val verId = arguments?.getString(PhoneFragment.VER_ID_KEY)
        binding.btnVerify.setOnClickListener {
            val credential =
                PhoneAuthProvider.getCredential(verId!!, binding.userVerify.text.toString())
            signInWithPhoneAuthCredential(credential)
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        FirebaseAuth.getInstance().signInWithCredential(credential)
            .addOnSuccessListener {
                findNavController().navigate(R.id.navigation_home)
            }.addOnFailureListener {
                showToast(it.message.toString())
            }
    }

}