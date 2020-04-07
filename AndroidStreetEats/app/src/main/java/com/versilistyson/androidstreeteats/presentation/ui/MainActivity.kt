package com.versilistyson.androidstreeteats.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.NavArgument
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.versilistyson.androidstreeteats.NavGraphMainDirections
import com.versilistyson.androidstreeteats.R
import com.versilistyson.androidstreeteats.data.firebase.models.AccountType
import com.versilistyson.androidstreeteats.databinding.ActivityMainBinding
import com.versilistyson.androidstreeteats.di.injector
import com.versilistyson.androidstreeteats.di.util.DaggerViewModelFactory
import com.versilistyson.androidstreeteats.presentation.util.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: DaggerViewModelFactory

    private val navDestinationIdGroups = NavDestinationIdGroups
    private val appViewModel: AppViewModel by viewModels { viewModelFactory }
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
        binding.lifecycleOwner = this
    }

    override fun onStart() {
        super.onStart()
        /*appViewModel.fireAuthInstance.signOut()*/
        setupNavigation()
        setupToolbar()
        setupBottomNavBar()
        /*setBottomNavAndToolbarVisibility(false) // TODO: Change later*/
        appViewModel.userState.observe(this, Observer(::renderUserState))
    }

    private fun renderUserState(userState: UserState) {
        when (userState) {
            is UserState.Authenticated -> {
                when (userState.userInfo.accountType) {
                    AccountType.BUSINESS -> {
                        binding.bottomNav.swapMenu(R.menu.menu_navbar_business)
                        navController.navigate(NavGraphMainDirections.actionGlobalNavGraphBusiness())
                    }
                    AccountType.CUSTOMER -> {
                        binding.bottomNav.swapMenu(R.menu.menu_navbar_customer)
                        navController.navigate(NavGraphMainDirections.actionGlobalNavGraphCustomer())
                    }
                }
                setBottomNavAndToolbarVisibility(isVisible = true)
            }
            is UserState.Unauthenticated -> {
                // Check if already on an authentication screen.
                // If not -- navigate to login fragment (since user would be logged in on any other fragment)
                if (
                    navController.currentDestination?.id !in navDestinationIdGroups.authentication.allDestinations
                ) {
                    navController.navigate(NavGraphMainDirections.actionGlobalNavGraphAuthentication())
                }
                setBottomNavAndToolbarVisibility(isVisible = false)
            }
        }
    }
    private fun handleAppEvent() {
        TODO()
    }

    private fun setupToolbar() {
        val topLevelDestinationIds = navDestinationIdGroups.customer.startDestinations + navDestinationIdGroups.business.startDestinations
        val appBarConfiguration =
            AppBarConfiguration(topLevelDestinationIds)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
        binding.toolbar.setOnMenuItemClickListener {menuItem ->
            menuItem.onNavDestinationSelected(navController)
        }
    }

    private fun setupBottomNavBar() {
        binding.bottomNav.setupWithNavController(navController)
        binding.bottomNav.setOnNavigationItemSelectedListener { menutItem ->
            menutItem.onNavDestinationSelected(navController)
        }
    }

    private fun setupNavigation() {
        navController = binding.navHostContainer.findNavController()
        navController.setGraph(R.navigation.nav_graph_main)
        onDestinationChanged()
    }

    private fun onDestinationChanged() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            // Change toolbar menu Based on Destination.
            // Clears toolbar menu conditionally
            when (destination.id) {
                R.id.businessDashboardFragment ->
                    binding.toolbar.swapMenu(R.menu.menu_toolbar_business_dashboard)
                R.id.splashFragment ->
                    setBottomNavAndToolbarVisibility(isVisible = false)
                else ->
                    binding.toolbar.clearMenu()
            }
        }
    }

    private fun setBottomNavAndToolbarVisibility(isVisible: Boolean) {
        binding.toolbar.isVisible = isVisible
        binding.bottomNav.isVisible = isVisible
    }
}
