package com.bankuish.challenge.presentation.repositories.list

import android.content.res.Configuration
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.bankuish.challenge.R
import com.bankuish.challenge.app.theme.*
import com.bankuish.challenge.app.widget.LoadingView
import com.bankuish.challenge.domain.model.Repositories
import org.koin.androidx.compose.koinViewModel

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, showSystemUi = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = true)
@Composable
private fun Preview() {
    val navController = rememberNavController()
    RepositoriesListView(navController)
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RepositoriesListView(
    navController: NavController,
    viewModel: RepositoriesListViewModel = koinViewModel()
) {
    val repositories by remember {
        mutableStateOf(viewModel.getRepositories())
    }
    val pagingData = repositories.collectAsLazyPagingItems()

    Scaffold(topBar = {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .background(if (isSystemInDarkTheme()) secondary else primary)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.github_mark),
                contentDescription = "ic_github",
                tint = Color.White,
                modifier = Modifier
                    .size(35.dp)
            )
            Spacer(modifier = Modifier.width(15.dp))
            Text(
                text = "Repositories",
                color = Color.White,
                fontSize = 35.sp
            )
        }
    }) {
        val pullState =
            rememberPullRefreshState(refreshing = viewModel.isRefreshing, onRefresh = {
                viewModel.refresh(pagingData)
            })
        Box(
            modifier = Modifier
                .padding(it)
                .clipToBounds()
                .pullRefresh(pullState)
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .background(if (isSystemInDarkTheme()) secondaryContainer else primaryContainer)
                    .padding(top = 20.dp)
            ) {
                if (pagingData.loadState.refresh is LoadState.Loading && !viewModel.isRefreshing) {
                    item {
                        LoadingView(isLoading = true)
                    }
                }
                when (pagingData.loadState.refresh) {
                    is LoadState.NotLoading -> items(pagingData) { repo ->
                        repo?.let {
                            Item(repo = repo,viewModel,navController)
                        }
                    }
                    is LoadState.Error -> item {
                        ErrorPaging()
                    }
                    else -> {}
                }
                when (pagingData.loadState.append) {
                    is LoadState.Loading -> item {
                        LoadingPaging()
                    }
                    is LoadState.Error -> item {
                        ErrorPaging()
                    }
                    else -> {}
                }
            }
            PullRefreshIndicator(
                refreshing = viewModel.isRefreshing,
                state = pullState,
                backgroundColor = if (isSystemInDarkTheme()) onSecondary else onPrimary,
                contentColor = green,
                scale = true,
                modifier = Modifier
                    .align(Alignment.TopCenter)
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Item(repo: Repositories,viewModel: RepositoriesListViewModel,navController: NavController) {
    Card(
        elevation = 3.dp,
        backgroundColor = if (isSystemInDarkTheme()) onSecondary else onPrimary,
        modifier = Modifier
            .height(80.dp)
            .width(300.dp)
            .combinedClickable(
                onClick = { viewModel.getDetails(repo, navController) },
            )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = repo.name.uppercase(),
                fontSize = 20.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                color = if (isSystemInDarkTheme()) onPrimary else onSecondary,
                modifier = Modifier
                    .padding(horizontal = 20.dp)
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = repo.owner.nombre,
                fontSize = 14.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                color = if (isSystemInDarkTheme()) onPrimary.copy(.5f) else onSecondary.copy(.5f),
                modifier = Modifier
                    .padding(horizontal = 30.dp)
            )
        }
    }
}

@Composable
private fun LoadingPaging() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
    ) {
        CircularProgressIndicator(color = green)
    }
}

@Composable
private fun ErrorPaging() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp)
    ) {
        Text(
            text = "An error has occurred getting the information,\nplease try again.",
            color = red,
            textAlign = TextAlign.Center
        )
    }
}
