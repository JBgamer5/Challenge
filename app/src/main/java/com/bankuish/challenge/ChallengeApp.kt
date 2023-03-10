package com.bankuish.challenge

import android.app.Application
import com.bankuish.challenge.data.dataModule
import com.bankuish.challenge.domain.usecasesModule
import com.bankuish.challenge.presentation.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ChallengeApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@ChallengeApp)
            modules(dataModule, viewModelModule, usecasesModule)
        }
    }
}