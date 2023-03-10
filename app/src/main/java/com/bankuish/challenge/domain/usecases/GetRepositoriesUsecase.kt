package com.bankuish.challenge.domain.usecases

import androidx.paging.PagingData
import com.bankuish.challenge.domain.model.Repositories
import com.bankuish.challenge.domain.repository.RepositoriesRepository
import kotlinx.coroutines.flow.Flow

class GetRepositoriesUsecase(private val repository: RepositoriesRepository) {

    operator fun invoke(): Flow<PagingData<Repositories>> {
        return repository.getRepositories()
    }

}