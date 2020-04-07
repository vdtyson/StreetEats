package com.versilistyson.androidstreeteats.presentation.util

data class NavDestinationIdGroup(private val navDestinationResources: Set<NavDestinationResource>) {

    val startDestinations: Set<Int>
    val allDestinations: Set<Int>

    init {
        val startDestinationsList = mutableListOf<Int>()
        val allDestinationsList = mutableListOf<Int>()
        navDestinationResources.forEach {navDestinationResource ->
            if(navDestinationResource.isStartDestination) {
               startDestinationsList.add(navDestinationResource.id)
            }
            allDestinationsList.add(navDestinationResource.id)
        }
        startDestinations = startDestinationsList.toSet()
        allDestinations = allDestinationsList.toSet()
    }

    operator fun contains(navDestinationResource: NavDestinationResource?) : Boolean {
        val destinationResource = navDestinationResource ?: return false
        return if(destinationResource.isStartDestination) {
            startDestinations.contains(destinationResource.id)
        } else {
            allDestinations.contains(destinationResource.id)
        }
    }

    operator fun plus(navDestinationIdGroup: NavDestinationIdGroup): NavDestinationIdGroup =
        NavDestinationIdGroup(navDestinationResources + navDestinationIdGroup.navDestinationResources)

    operator fun plus(navDestinationResource: NavDestinationResource): NavDestinationIdGroup =
        NavDestinationIdGroup(navDestinationResources + navDestinationResource)
}
