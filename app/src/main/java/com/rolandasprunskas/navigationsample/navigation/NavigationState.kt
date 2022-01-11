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
        override val bundle: Bundle?
            get() = null

        override fun shouldShow(): Boolean {
            return true
        }
    }

    object IDPassportState : NavigationState(ID_PASSPORT_SCREEN_ORDER) {
        override val navId: Int
            get() = R.id.IDPasswordFragmentVerification
        override val bundle: Bundle
            get() = bundleOf(ID_DATA_KEY to "sample_data")

        override fun shouldShow(): Boolean {
            return NavigationSession.IDPassportVerification
        }
    }

    object EIDState : NavigationState(EID_SCREEN_ORDER) {
        override val navId: Int
            get() = R.id.EIDFragment
        override val bundle: Bundle?
            get() = null

        override fun shouldShow(): Boolean {
            return !NavigationSession.IDPassportVerification
        }
    }

    object AddressState : NavigationState(ADDRESS_SCREEN_ORDER) {
        override val navId: Int
            get() = R.id.AddressFragment
        override val bundle: Bundle?
            get() = null

        override fun shouldShow(): Boolean {
            return NavigationSession.run {
                !IDPassportVerification && !EIDQualified
            }
        }
    }

    object IntermediateLevelState : NavigationState(INTERMEDIATE_SCREEN_ORDER) {
        override val navId: Int
            get() = R.id.IntermediateLevelFragment
        override val bundle: Bundle?
            get() = null

        override fun shouldShow(): Boolean {
            return NavigationSession.run {
                !IDPassportVerification && !EIDQualified // same conditions as AddressState
            }
        }
    }

    object AdvancedLevelState : NavigationState(ADVANCED_SCREEN_ORDER) {
        override val navId: Int
            get() = R.id.AdvancedLevelFragment
        override val bundle: Bundle?
            get() = null

        override fun shouldShow(): Boolean {
            return NavigationSession.run {
                IDPassportVerification || EIDQualified
            }
        }
    }

    object FinishState : NavigationState(FINISH_SCREEN_ORDER) {
        override val navId: Int
            get() = R.id.FinishFragment
        override val bundle: Bundle?
            get() = null

        override fun shouldShow(): Boolean {
            return true
        }
    }

    abstract fun shouldShow(): Boolean

    override fun compareTo(other: NavigationState): Int {
        return order.compareTo(other.order)
    }

    companion object {
        private const val ID_DATA_KEY = "id_data_key"
        private const val START_SCREEN_ORDER = 1
        private const val ID_PASSPORT_SCREEN_ORDER = 2
        private const val EID_SCREEN_ORDER = 3
        private const val ADDRESS_SCREEN_ORDER = 4
        private const val INTERMEDIATE_SCREEN_ORDER = 5
        private const val ADVANCED_SCREEN_ORDER = 6
        private const val FINISH_SCREEN_ORDER = 7
    }
}
