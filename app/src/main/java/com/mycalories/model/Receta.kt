package com.mycalories.model

import android.os.Parcelable
import androidx.room.*
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "Receta")
class Receta (
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    @ColumnInfo(name = "nombrereceta")
    val nombrereceta : String,
    @ColumnInfo(name = "receta")
    val contenidoreceta : String?
): Parcelable