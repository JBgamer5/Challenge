package com.bankuish.challenge.domain

import com.bankuish.challenge.domain.usecases.GetRepositoriesUsecase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val usecasesModule = module {

    factoryOf(::GetRepositoriesUsecase)

}
