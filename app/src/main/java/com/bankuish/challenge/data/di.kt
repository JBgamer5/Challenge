package com.bankuish.challenge.data

import com.bankuish.challenge.app.Qualifier
import com.bankuish.challenge.data.datasource.api.repositories.RepositoriesApiDataSource
import com.bankuish.challenge.data.paging.RepositoriesPagingSource
import com.bankuish.challenge.data.repository.repositories.RepositoriesRepositoryImpl
import com.bankuish.challenge.domain.repository.RepositoriesRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

val dataModule = module {
    single(named(Qualifier.ApiEndpoint)) { "https://api.github.com/search/" }
    single {
        Retrofit.Builder()
            .baseUrl(get<String>(named(Qualifier.ApiEndpoint)))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    factoryOf(::RepositoriesPagingSource)
    factoryOf(::RepositoriesRepositoryImpl) { bind<RepositoriesRepository>() }
    single { get<Retrofit>().create<RepositoriesApiDataSource>() }

}