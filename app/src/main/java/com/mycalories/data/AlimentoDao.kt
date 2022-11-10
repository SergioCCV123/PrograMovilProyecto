package com.mycalories.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mycalories.model.Alimento

@Dao
interface AlimentoDao {

    //las funciones de bajo nivel para hacer un CRUD
    //create Read Update Delete

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addAlimento(alimento: Alimento)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun udpateAlimento(alimento: Alimento)

    @Delete
    suspend fun deleteAlimento(alimento: Alimento)

    @Query("SELECT * FROM ALIMENTO")
    fun getAlimentos() : LiveData<List<Alimento>>

}