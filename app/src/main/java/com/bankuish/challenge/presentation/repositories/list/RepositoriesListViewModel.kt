package com.bankuish.challenge.presentation.repositories.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import com.bankuish.challenge.app.navigation.AppScreenNavigation
import com.bankuish.challenge.domain.model.Repositories
import com.bankuish.challenge.domain.usecases.GetRepositoriesUsecase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class RepositoriesListViewModel(private val getRepositoriesUsecase: GetRepositoriesUsecase) :
    ViewModel() {

    var isRefreshing by mutableStateOf(false)

    fun getRepositories(): Flow<PagingData<Repositories>> =
        getRepositoriesUsecase()

    fun refresh(pagingData: LazyPagingItems<Repositories>) {
        viewModelScope.launch {
            isRefreshing = true
            pagingData.refresh()
            delay(1000)
            isRefreshing = false
        }
    }

    fun getDetails(repo: Repositories, navController: NavController) {
        navController.currentBackStackEntry?.savedStateHandle?.set(
            key = "repo",
            repo
        )
        navController.navigate(AppScreenNavigation.DetailsRepositories.route)
    }

}