package com.mycalories.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.mycalories.model.Restaurante

@Dao
interface RestauranteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun saveRestaurante(restaurante: Restaurante)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateRestaurante(restaurante: Restaurante)

    @Delete
    abstract fun deleteRestaurante(restaurante: Restaurante)

    @Query("SELECT * FROM RESTAURANTE")
    abstract fun getRestaurantes(): LiveData<List<Restaurante>>
}