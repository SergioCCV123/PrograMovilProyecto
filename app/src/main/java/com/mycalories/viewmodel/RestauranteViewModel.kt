package com.mycalories.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.recetas.data.RecetaDatabase
import com.mycalories.data.RestauranteDao
import com.mycalories.data.RestauranteDatabase
import com.mycalories.model.Restaurante
import com.mycalories.repository.RecetaRepository
import com.mycalories.repository.RestauranteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RestauranteViewModel(application: Application) : AndroidViewModel(application) {

    private var restauranteRepository : RestauranteRepository
    val getRestaurante : LiveData<List<Restaurante>>

    init {
        val restauranteDao = RestauranteDatabase.getDatabase(application).restauranteDao()
        restauranteRepository = RestauranteRepository(restauranteDao)
        getRestaurante = restauranteRepository.getRestaurante
    }

    fun saveRestaurante (restaurante: Restaurante) {
        viewModelScope.launch(Dispatchers.IO){
            restauranteRepository.saveRestaurante(restaurante)
        }
    }

    fun deleteRestaurante (restaurante: Restaurante){
        viewModelScope.launch(Dispatchers.IO) {
            restauranteRepository.deleteRestaurante(restaurante)
        }
    }



}