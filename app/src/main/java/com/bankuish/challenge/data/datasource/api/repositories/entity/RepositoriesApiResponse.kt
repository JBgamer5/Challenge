package com.bankuish.challenge.data.datasource.api.repositories.entity

import com.google.gson.annotations.SerializedName

data class RepositoriesApiResponse(
    @SerializedName("incomplete_results") val incompleteResult: Boolean,
    @SerializedName("items") val items: List<RepositoriesApiEntity>,
    @SerializedName("total_count") val total_count: Int
)
