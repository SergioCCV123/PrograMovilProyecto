package com.mycalories.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "restaurante")
data class Restaurante(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    @ColumnInfo(name = "nombre")
    val nombre:String,
    @ColumnInfo(name = "telefono")
    val telefono:String,
    @ColumnInfo(name = "latitud")
    val latitud : Double,
    @ColumnInfo(name = "longitud")
    val longitud : Double,
    @ColumnInfo(name = "altura")
    val altura : Double?,

): Parcelable
