package com.rolandasprunskas.navigationsample.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rolandasprunskas.navigationsample.base.BaseNavigationFragment
import com.rolandasprunskas.navigationsample.databinding.FragmentAdvancedLevelBinding

class AdvancedLevelFragment : BaseNavigationFragment() {

    private var _binding: FragmentAdvancedLevelBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdvancedLevelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonNext.setOnClickListener {
            showNextScreen()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
