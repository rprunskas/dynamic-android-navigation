package com.rolandasprunskas.navigationsample.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rolandasprunskas.navigationsample.base.BaseNavigationFragment
import com.rolandasprunskas.navigationsample.databinding.FragmentEidBinding
import com.rolandasprunskas.navigationsample.navigation.NavigationSession
import kotlin.random.Random

class EIDFragment : BaseNavigationFragment() {

    private var _binding: FragmentEidBinding? = null

    private val binding get() = _binding!!

    private val handler = Handler(Looper.getMainLooper())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEidBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handler.postDelayed(
            {
                NavigationSession.EIDQualified = Random.nextBoolean()
                showNextScreen()
            },
            3000
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        handler.removeCallbacksAndMessages(null)
        _binding = null
    }
}
