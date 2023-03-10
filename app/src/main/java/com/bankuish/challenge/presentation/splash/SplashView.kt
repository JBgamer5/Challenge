package com.bankuish.challenge.presentation.splash

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bankuish.challenge.R
import com.bankuish.challenge.app.navigation.AppScreenNavigation
import com.bankuish.challenge.app.theme.onPrimary
import com.bankuish.challenge.app.theme.onSecondary
import com.bankuish.challenge.app.theme.primaryContainer
import com.bankuish.challenge.app.theme.secondaryContainer
import kotlinx.coroutines.delay

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, showSystemUi = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = true)
@Composable
private fun Preview() {
    val navController = rememberNavController()
    SplashView(navController)
}

@Composable
fun SplashView(navController: NavHostController) {
    LaunchedEffect(Unit) {
        delay(2500)
        navController.popBackStack()
        navController.navigate(AppScreenNavigation.ListRepositories.route)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(if (isSystemInDarkTheme()) secondaryContainer else primaryContainer),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            color = if (isSystemInDarkTheme()) onPrimary else onSecondary,
            shape = CircleShape,
            modifier = Modifier
                .size(200.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.github_mark),
                contentDescription = "ic_splash",
                tint = if (isSystemInDarkTheme()) onSecondary else onPrimary,
                modifier = Modifier
                    .padding(30.dp)
            )
        }
    }
}