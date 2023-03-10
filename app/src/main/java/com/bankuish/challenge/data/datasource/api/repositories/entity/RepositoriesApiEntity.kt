package com.bankuish.challenge.data.datasource.api.repositories.entity

import com.bankuish.challenge.domain.model.Repositories
import com.google.gson.annotations.SerializedName

data class RepositoriesApiEntity(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("html_url") val url: String,
    @SerializedName("description") val description: String?,
    @SerializedName("watchers") val watchers: Int,
    @SerializedName("forks") val forks: Int,
    @SerializedName("owner") val owner: OwnerApiEntity,
    @SerializedName("topics") val topics: List<String>?
)

fun RepositoriesApiEntity.toRepositories(): Repositories {
    return Repositories(id, name, url, description ?: "", watchers, forks, owner.toOwner(), topics)
}