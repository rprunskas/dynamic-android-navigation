package com.rolandasprunskas.navigationsample.navigation

import android.os.Bundle
import androidx.core.os.bundleOf
import com.rolandasprunskas.navigationsample.R

sealed class NavigationState(
    private val order: Int
) : Comparable<NavigationState>, Screen {

    object IntroState : NavigationState(START_SCREEN_ORDER) {
        override val navId: Int
            get() = R.id.IntroFragment
        override val bundle: Bundle
            get() = bundleOf()

        override fun shouldShow(): Boolean {
            return true
        }
    }

    object IDPassportState : NavigationState(ID_PASSPORT_SCREEN_ORDER) {
        override val navId: Int
            get() = R.id.IDPasswordFragmentVerification
        override val bundle: Bundle
            get() = bundleOf()

        override fun shouldShow(): Boolean {
            return NavigationSession.IDPassportVerification
        }
    }

    object EIDState : NavigationState(EID_SCREEN_ORDER) {
        override val navId: Int
            get() = R.id.EIDFragment
        override val bundle: Bundle
            get() = bundleOf()

        override fun shouldShow(): Boolean {
            return !NavigationSession.IDPassportVerification
        }
    }

    object AddressState : NavigationState(ADDRESS_SCREEN_ORDER) {
        override val navId: Int
            get() = R.id.AddressFragment
        override val bundle: Bundle
            get() = bundleOf()

        override fun shouldShow(): Boolean {
            return NavigationSession.run {
                !IDPassportVerification && !EIDQualified
            }
        }
    }

    object IntermediateLevelState : NavigationState(INTERMEDIATE_SCREEN_ORDER) {
        override val navId: Int
            get() = R.id.IntermediateLevelFragment
        override val bundle: Bundle
            get() = bundleOf()

        override fun shouldShow(): Boolean {
            return NavigationSession.run {
                !IDPassportVerification && !EIDQualified // same conditions as AddressState
            }
        }
    }

    object AdvancedLevelState : NavigationState(ADVANCED_SCREEN_ORDER) {
        override val navId: Int
            get() = R.id.AdvancedLevelFragment
        override val bundle: Bundle
            get() = bundleOf()

        override fun shouldShow(): Boolean {
            return NavigationSession.run {
                IDPassportVerification || EIDQualified
            }
        }
    }

    object FinishState : NavigationState(FINISH_SCREEN_ORDER) {
        override val navId: Int
            get() = R.id.FinishFragment
        override val bundle: Bundle
            get() = bundleOf()

        override fun shouldShow(): Boolean {
            return true
        }
    }

    abstract fun shouldShow(): Boolean

    override fun compareTo(other: NavigationState): Int {
        return order.compareTo(other.order)
    }

    companion object {
        const val START_SCREEN_ORDER = 1
        const val ID_PASSPORT_SCREEN_ORDER = 2
        const val EID_SCREEN_ORDER = 3
        const val ADDRESS_SCREEN_ORDER = 4
        const val INTERMEDIATE_SCREEN_ORDER = 5
        const val ADVANCED_SCREEN_ORDER = 6
        const val FINISH_SCREEN_ORDER = 7
    }
}
