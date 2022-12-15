package com.mycalories.model

import android.os.Parcelable
import androidx.room.*
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "Rutinas")
data class Rutinas(

    @PrimaryKey(autoGenerate = true)
    val RId:Int,
    @ColumnInfo(name="nombre")
    val nombre: String,
    @ColumnInfo(name = "ejeryreps")
    val EjeryReps: String,
    @ColumnInfo(name = "reposo")
    val reposo: String,
): Parcelable
