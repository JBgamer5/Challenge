package com.bankuish.challenge.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Repositories(
    val id: Int,
    val name: String,
    val html_url: String,
    val description: String,
    val stars: Int,
    val forks: Int,
    val owner: Owner,
    val topics: List<String>?
):Parcelable
