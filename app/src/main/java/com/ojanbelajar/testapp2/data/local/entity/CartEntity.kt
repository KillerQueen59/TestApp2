package com.ojanbelajar.testapp2.data.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName ="carts")
@Parcelize
data class CartEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name ="id")
    val id : Int = 0,

    @NonNull
    @ColumnInfo(name="name")
    var name: String = "",

    @NonNull
    @ColumnInfo(name="price")
    var price: Int = 0,

    @ColumnInfo(name="half")
    var half: Boolean = false,

    @ColumnInfo(name="whole")
    var whole: Boolean = false,

    @NonNull
    @ColumnInfo(name="quantity")
    var qty: Int = 0,

    @ColumnInfo(name="notes")
    var notes: String = "",


    @ColumnInfo(name="taxes")
    var taxes: Int = 0,

    @NonNull
    @ColumnInfo(name="total")
    var total: Int = 0,
): Parcelable
