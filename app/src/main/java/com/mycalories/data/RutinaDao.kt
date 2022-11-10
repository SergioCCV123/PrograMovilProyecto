package com.mycalories.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mycalories.model.Rutinas

@Dao
interface RutinaDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addRutina(rutinas: Rutinas)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateRutina(rutinas: Rutinas)

    @Delete
    suspend fun deleteRutina(rutinas: Rutinas)

    @Query("SELECT * FROM RUTINAS")
    fun getRutinas() : LiveData<List<Rutinas>>

}