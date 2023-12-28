package com.example.taskmaneger.ui.onboarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.taskmaneger.databinding.ItemOnboardingBinding
import com.example.taskmaneger.model.OnBoarding
import com.example.taskmaneger.utils.loadImage

class OnBoardingAdapter(private val onClick: () -> Unit) :
    Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {
    private val list = arrayListOf(
        OnBoarding("https://biznext.in/images/jan-2022/Image-1.png", "Welcome Aboard!", "Get started with your new account in just a few steps."),
        OnBoarding("https://biznext.in/images/jan-2022/Image-1.png", "Simple Steps to Secure Banking", "Your security is our priority."),
        OnBoarding("https://biznext.in/images/jan-2022/Image-1.png", "Financial Freedom!", "Embark on a journey towards financial well-being."),
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(
            ItemOnboardingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class OnBoardingViewHolder(private val binding: ItemOnboardingBinding) :
        ViewHolder(binding.root) {
        fun bind(onBoarding: OnBoarding) = with(binding) {
            tvTitle.text = onBoarding.title
            tvDesc.text = onBoarding.desc
            ivBoard.loadImage(onBoarding.image)
            skip.setOnClickListener {onClick()}
            btnStart.setOnClickListener {onClick()}
            skip.isInvisible = adapterPosition != list.lastIndex
            btnStart.isVisible = adapterPosition == list.lastIndex
        }
    }
}