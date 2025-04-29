package za.co.todoapp.common.services.navigation

import kotlinx.serialization.Serializable

sealed interface Destination {
    @Serializable
    data object HomeGraph: Destination

    @Serializable
    data object HomeScreen: Destination

    @Serializable
    data object MenuScreen: Destination

    @Serializable
    data object TaskScreen: Destination


}