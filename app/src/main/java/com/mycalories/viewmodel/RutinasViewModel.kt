package com.mycalories.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.mycalories.data.RutinasDatabase
import com.mycalories.model.Rutinas
import com.mycalories.repository.RutinasRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class RutinasViewModel(application: Application) : AndroidViewModel(application){

    private val rutinasRepository: RutinasRepository
    val getRutinas: LiveData<List<Rutinas>>

    init {
        val rutinaDao = RutinasDatabase.getDatabase(application).rutinasDao()
        rutinasRepository = RutinasRepository(rutinaDao)
        getRutinas = rutinasRepository.getRutinas
    }

    fun saveRutinas(rutinas: Rutinas){
        viewModelScope.launch(Dispatchers.IO){
            rutinasRepository.saveRutina(rutinas)
        }
    }

    fun deleteRutinas(rutinas: Rutinas){
        viewModelScope.launch(Dispatchers.IO){
            rutinasRepository.deleteRutina(rutinas)
        }
    }

}