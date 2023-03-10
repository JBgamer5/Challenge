package com.bankuish.challenge.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Owner(val id: Int, val nombre: String):Parcelable
