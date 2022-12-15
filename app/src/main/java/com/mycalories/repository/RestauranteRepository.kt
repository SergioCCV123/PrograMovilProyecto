package com.mycalories.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mycalories.data.RestauranteDao
import com.mycalories.model.Restaurante

class RestauranteRepository(private val restauranteDao: RestauranteDao) {

    suspend fun saveRestaurante(restaurante : Restaurante){
        restauranteDao.saveRestaurante(restaurante)
    }

    suspend fun deleteRestaurante(restaurante: Restaurante){
        restauranteDao.deleteRestaurante(restaurante)
    }

    val getRestaurante : LiveData<List<Restaurante>> = restauranteDao.getRestaurantes()

}