package com.bankuish.challenge.data.repository.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.bankuish.challenge.data.datasource.api.repositories.RepositoriesApiDataSource
import com.bankuish.challenge.data.paging.RepositoriesPagingSource
import com.bankuish.challenge.domain.model.Repositories
import com.bankuish.challenge.domain.repository.RepositoriesRepository
import kotlinx.coroutines.flow.Flow

class RepositoriesRepositoryImpl(private val api: RepositoriesApiDataSource) :
    RepositoriesRepository {

    override fun getRepositories(): Flow<PagingData<Repositories>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                prefetchDistance = 5,
                enablePlaceholders = false,
                initialLoadSize = 10
            ),
            pagingSourceFactory = { RepositoriesPagingSource(api) }
        ).flow
    }

}
