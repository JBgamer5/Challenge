package com.bankuish.challenge.app.navigation

sealed class AppScreenNavigation(val route: String) {
    object ListRepositories : AppScreenNavigation("listRepositories")
    object DetailsRepositories : AppScreenNavigation("detailsRepositories")
    object Splash : AppScreenNavigation("splash")
}
