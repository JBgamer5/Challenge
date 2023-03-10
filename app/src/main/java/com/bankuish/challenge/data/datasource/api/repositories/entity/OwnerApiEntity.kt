package com.bankuish.challenge.data.datasource.api.repositories.entity

import com.bankuish.challenge.domain.model.Owner
import com.google.gson.annotations.SerializedName

data class OwnerApiEntity(
    @SerializedName("id") val id: Int,
    @SerializedName("login") val nombre: String
)

fun OwnerApiEntity.toOwner(): Owner {
    return Owner(id,nombre)
}