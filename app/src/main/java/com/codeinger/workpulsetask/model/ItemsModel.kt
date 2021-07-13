package com.codeinger.workpulsetask.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity (tableName = "news_table")
data class ItemsModel(
    @PrimaryKey()
    val id: Int? = null,
    val score: Int? = null,
    val by: String? = null,
    val time: Int? = null,
    val title: String? = null,
    val type: String? = null,
    val descendants: Int? = null,
    val url: String? = null,
) : Parcelable
