package com.mycalories.repository

import androidx.lifecycle.LiveData
import com.mycalories.data.RutinaDao
import com.mycalories.model.Rutinas

class RutinasRepository(private val rutinaDao:RutinaDao) {

    suspend fun saveRutina(rutinas: Rutinas){
        if (rutinas.RId==0){
            rutinaDao.addRutina(rutinas)
        }else{
            rutinaDao.updateRutina(rutinas)
        }
    }

    suspend fun deleteRutina(rutinas: Rutinas){
        if (rutinas.RId!=0){
            rutinaDao.deleteRutina(rutinas)
        }
    }

    val getRutinas: LiveData<List<Rutinas>> = rutinaDao.getRutinas()
}