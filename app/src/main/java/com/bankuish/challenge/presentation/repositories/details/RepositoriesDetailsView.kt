package com.bankuish.challenge.presentation.repositories.details

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bankuish.challenge.R
import com.bankuish.challenge.app.theme.*
import com.bankuish.challenge.domain.model.Owner
import com.bankuish.challenge.domain.model.Repositories
import org.koin.androidx.compose.koinViewModel

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, showSystemUi = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = true)
@Composable
private fun Preview() {
    val repo = Repositories(0, "", "", "", 0, 0, Owner(0, ""), emptyList())
    val navController = rememberNavController()
    RepositoriesDetailsView(repo, navController)
}

@Composable
fun RepositoriesDetailsView(
    repo: Repositories,
    navController: NavController,
    viewModel: RepositoriesDetailsViewModel = koinViewModel()
) {
    val textColor = if (isSystemInDarkTheme()) onPrimary.copy(.8f) else onSecondary.copy(.8f)
    BackHandler {
        navController.popBackStack()
    }
    Scaffold(topBar = {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(if (isSystemInDarkTheme()) secondary else primary)
        ) {
            Text(
                text = repo.name.uppercase(),
                color = onPrimary,
                fontSize = 35.sp,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(horizontal = 30.dp, vertical = 15.dp)
            )
        }
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(if (isSystemInDarkTheme()) secondaryContainer else primaryContainer)
                .padding(paddingValues)
                .padding(top = 30.dp)
                .padding(horizontal = 15.dp)
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .weight(9f)
            ) {
                item {
                    Text(
                        text = "Author: ${repo.owner.nombre}",
                        color = textColor,
                        fontSize = 17.sp
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Divider(color = textColor)
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Row(verticalAlignment = Alignment.Bottom) {
                            Icon(
                                painter = painterResource(id = R.drawable.github_fork),
                                contentDescription = "ic_forks",
                                tint = textColor,
                                modifier = Modifier
                                    .size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "%,d".format(repo.forks),
                                color = textColor,
                                fontSize = 17.sp
                            )
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Filled.StarBorder,
                                contentDescription = "ic_stars",
                                tint = textColor,
                                modifier = Modifier
                                    .size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "%,d".format(repo.stars),
                                color = textColor,
                                fontSize = 17.sp
                            )
                        }
                    }
                }
                //description
                item {
                    Text(
                        text = repo.description,
                        color = textColor,
                        fontSize = 15.sp
                    )
                }

                val topicStyle: @Composable (String) -> Unit = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(start = 15.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Circle,
                            contentDescription = "text_decoration",
                            tint = textColor,
                            modifier = Modifier
                                .size(8.dp)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = it.replaceFirstChar { it.uppercase() },
                            color = textColor,
                            fontSize = 15.sp
                        )
                    }
                }
                //topics
                if (!repo.topics.isNullOrEmpty()) {
                    item {
                        Column {
                            Text(
                                text = "Topics:",
                                color = textColor,
                                fontSize = 15.sp
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            for (t in repo.topics) {
                                topicStyle(t)
                            }
                        }
                    }
                }
            }
            Box(
                modifier = Modifier
                    .weight(1f)
            ) {
                val uriHandler = LocalUriHandler.current
                Button(
                    onClick = {
                        viewModel.goToRepository(repo.html_url, uriHandler)
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = green
                    ),
                    modifier = Modifier
                        .height(50.dp)
                        .padding(horizontal = 20.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Go to repository",
                        fontSize = 17.sp,
                        color = if (isSystemInDarkTheme()) secondaryContainer else primaryContainer
                    )
                }
            }
        }
    }
}