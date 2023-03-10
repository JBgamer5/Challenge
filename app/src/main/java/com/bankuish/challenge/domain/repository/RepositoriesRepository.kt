package com.bankuish.challenge.domain.repository

import androidx.paging.PagingData
import com.bankuish.challenge.domain.model.Repositories
import kotlinx.coroutines.flow.Flow

interface RepositoriesRepository {
    fun getRepositories(): Flow<PagingData<Repositories>>
}