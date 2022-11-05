package com.mycalories.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "Usuario")
data class Usuario(

    @PrimaryKey()
    val uid:String,
    @ColumnInfo(name="nombre")
    val nombre:String,
    @ColumnInfo(name="telefono")
    val telefono : String?,
    @ColumnInfo(name="altura")
    val altura : Double?,
    @ColumnInfo(name="Peso")
    val peso : Double?,
    @ColumnInfo(name="BMI")
    val bmi : Double?,

) : Parcelable
