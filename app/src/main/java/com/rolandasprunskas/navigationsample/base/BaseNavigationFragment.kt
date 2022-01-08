package com.rolandasprunskas.navigationsample.base

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.rolandasprunskas.navigationsample.navigation.AppNavigator

abstract class BaseNavigationFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val backScreen = AppNavigator.goBack()
                    backScreen?.let {
                        findNavController().popBackStack(it.navId, false)
                    } ?: run {
                        requireActivity().finish()
                    }
                }
            }
        )
    }

    protected fun showNextScreen() {
        val nextScreen = AppNavigator.goNext()
        findNavController().navigate(nextScreen.navId, nextScreen.bundle)
    }
}
