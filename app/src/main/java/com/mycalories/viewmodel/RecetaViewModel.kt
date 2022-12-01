package com.mycalories.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.recetas.data.RecetaDatabase
import com.mycalories.model.Receta
import com.mycalories.repository.RecetaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecetaViewModel(application: Application) : AndroidViewModel(application) {

    private val recetaRepository : RecetaRepository
    val getRecetas: LiveData<List<Receta>>

    init {
        val recetaDao = RecetaDatabase.getDatabase(application).recetaDao()
        recetaRepository = RecetaRepository(recetaDao)
        getRecetas = recetaRepository.getRecetas
    }

    fun saveReceta(receta: Receta){
        viewModelScope.launch(Dispatchers.IO ) {
            recetaRepository.saveReceta(receta)
        }
    }

    fun deleteReceta(receta: Receta){
        viewModelScope.launch(Dispatchers.IO ) {
            recetaRepository.deleteReceta(receta)
        }
    }
}