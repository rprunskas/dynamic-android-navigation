package com.rolandasprunskas.navigationsample.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rolandasprunskas.navigationsample.base.BaseNavigationFragment
import com.rolandasprunskas.navigationsample.databinding.FragmentFinishBinding

class FinishFragment : BaseNavigationFragment() {

    private var _binding: FragmentFinishBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFinishBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
