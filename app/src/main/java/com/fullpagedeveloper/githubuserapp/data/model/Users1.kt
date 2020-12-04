package com.fullpagedeveloper.githubuserapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class User1(
    val users : List<Users1>
)

@Parcelize
data class Users1(
    val username: String,
    val name: String,
    val avatar: String,
    val company: String,
    val location: String,
    val repository: Int,
    val follower: Int,
    val following: Int
): Parcelable