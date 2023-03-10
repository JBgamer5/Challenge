package com.bankuish.challenge.data.datasource.api.repositories

import com.bankuish.challenge.data.datasource.api.repositories.entity.RepositoriesApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RepositoriesApiDataSource {

    @GET("repositories")
    suspend fun getRepositories(
        @Query("q") q: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int
    ): RepositoriesApiResponse

}