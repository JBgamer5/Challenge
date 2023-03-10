package com.bankuish.challenge.presentation


import com.bankuish.challenge.presentation.repositories.details.RepositoriesDetailsViewModel
import com.bankuish.challenge.presentation.repositories.list.RepositoriesListViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::RepositoriesListViewModel)
    viewModelOf(::RepositoriesDetailsViewModel)
}