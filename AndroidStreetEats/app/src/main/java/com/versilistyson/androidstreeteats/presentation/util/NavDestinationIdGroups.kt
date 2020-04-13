package com.versilistyson.androidstreeteats.presentation.util

import com.versilistyson.androidstreeteats.R

object NavDestinationIdGroups {
    val authentication: NavDestinationIdGroup =
        NavDestinationIdGroup(
            setOf(
                NavDestinationResource(R.id.authenticationMainFragment, true),
                NavDestinationResource(R.id.loginFragment),
                NavDestinationResource(R.id.signupFragment)
            )
        )


    val customer: NavDestinationIdGroup =
        NavDestinationIdGroup(
            setOf(
                NavDestinationResource(R.id.customerProfileFragment, true),
                NavDestinationResource(R.id.vendorsContainerFragment, true),
                NavDestinationResource(R.id.foodCartFragment, true)
            )
        )


    val business: NavDestinationIdGroup =
        NavDestinationIdGroup(
            setOf(
                NavDestinationResource(R.id.businessTrackFragment, true),
                NavDestinationResource(R.id.businessStorefrontsFragment, true),
                NavDestinationResource(R.id.businessSettingsFragment, true),
                NavDestinationResource(R.id.businessDashboardFragment, true),
                NavDestinationResource(R.id.businessSettingsFragment)
            )
        )

    val splash: NavDestinationIdGroup =
        NavDestinationIdGroup(setOf(NavDestinationResource(R.id.splashFragment, true)))

    val destinationsWithNoAppBar: NavDestinationIdGroup =
        authentication + splash
}