package com.bankuish.challenge.app

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bankuish.challenge.app.navigation.AppScreenNavigation
import com.bankuish.challenge.app.theme.ChallengeTheme
import com.bankuish.challenge.app.theme.onSecondary
import com.bankuish.challenge.domain.model.Repositories
import com.bankuish.challenge.presentation.repositories.details.RepositoriesDetailsView
import com.bankuish.challenge.presentation.repositories.list.RepositoriesListView
import com.bankuish.challenge.presentation.splash.SplashView
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemUiController = rememberSystemUiController()
            systemUiController.setSystemBarsColor(onSecondary, darkIcons = false)

            ChallengeTheme {
                Content()
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, showSystemUi = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = true)
@Composable
private fun Content() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreenNavigation.Splash.route
    ) {
        composable(AppScreenNavigation.ListRepositories.route) {
            RepositoriesListView(navController = navController)
        }
        composable(AppScreenNavigation.DetailsRepositories.route) {
            navController.previousBackStackEntry?.savedStateHandle?.get<Repositories>("repo")
                ?.let { repo ->
                    RepositoriesDetailsView(repo = repo, navController = navController)
                }
        }
        composable(AppScreenNavigation.Splash.route){
            SplashView(navController = navController)
        }
    }
}