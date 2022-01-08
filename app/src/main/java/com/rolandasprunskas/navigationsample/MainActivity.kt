package com.rolandasprunskas.navigationsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.rolandasprunskas.navigationsample.databinding.ActivityMainBinding
import com.rolandasprunskas.navigationsample.navigation.AppNavigator
import com.rolandasprunskas.navigationsample.navigation.NavigationState.AddressState
import com.rolandasprunskas.navigationsample.navigation.NavigationState.AdvancedLevelState
import com.rolandasprunskas.navigationsample.navigation.NavigationState.EIDState
import com.rolandasprunskas.navigationsample.navigation.NavigationState.FinishState
import com.rolandasprunskas.navigationsample.navigation.NavigationState.IDPassportState
import com.rolandasprunskas.navigationsample.navigation.NavigationState.IntermediateLevelState
import com.rolandasprunskas.navigationsample.navigation.NavigationState.IntroState

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        initNavigation()
    }

    private fun initNavigation() {
        AppNavigator.init(
            sortedSetOf(
                IntroState,
                IDPassportState,
                EIDState,
                AddressState,
                IntermediateLevelState,
                AdvancedLevelState,
                FinishState
            )
        )
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)
        navGraph.startDestination = AppNavigator.findStart().navId
        navController.graph = navGraph
        appBarConfiguration = AppBarConfiguration(navGraph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        AppNavigator.goBack()?.let {
            navController.popBackStack(it.navId, false)
        } ?: run {
            finish()
        }
        return true
    }
}
