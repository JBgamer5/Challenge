package com.bankuish.challenge.presentation.repositories.details

import androidx.compose.ui.platform.UriHandler
import androidx.lifecycle.ViewModel

class RepositoriesDetailsViewModel : ViewModel() {

    fun goToRepository(htmlUrl: String, uriHandler: UriHandler) {
        uriHandler.openUri(htmlUrl)
    }

}