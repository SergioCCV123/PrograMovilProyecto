package com.mycalories.model

import android.os.Parcelable
import androidx.room.*
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "alimento")
class Alimento (
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    @ColumnInfo(name = "alimento")
    val alimento : String,
    @ColumnInfo(name = "calorias")
    val calorias : String?
): Parcelable