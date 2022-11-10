package com.mycalories.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.mycalories.data.AlimentoDatabase
import com.mycalories.model.Alimento
import com.mycalories.repository.AlimentoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlimentoViewModel(application: Application) : AndroidViewModel(application){

    private val alimentoRepository : AlimentoRepository
    val getAlimentos: LiveData<List<Alimento>>

    init {
        val alimentoDao = AlimentoDatabase.getDatabase(application).alimentoDao()
        alimentoRepository = AlimentoRepository(alimentoDao)
        getAlimentos = alimentoRepository.getAlimentos
    }

    fun saveAlimento(alimento: Alimento){
        viewModelScope.launch(Dispatchers.IO ) {
            alimentoRepository.saveAlimento(alimento)
        }
    }

    fun deleteAlimento(alimento: Alimento){
        viewModelScope.launch(Dispatchers.IO ) {
            alimentoRepository.deleteAlimento(alimento)
        }
    }

}