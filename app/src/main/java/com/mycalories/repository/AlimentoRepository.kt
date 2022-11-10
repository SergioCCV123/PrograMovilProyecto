package com.mycalories.repository

import androidx.lifecycle.LiveData
import com.mycalories.model.Alimento
import com.mycalories.data.AlimentoDao

class AlimentoRepository(private val alimentoDao: AlimentoDao) {

    suspend fun saveAlimento(alimento: Alimento){
        if(alimento.id==0){//es un lugar nuevo
            alimentoDao.addAlimento(alimento)
        }else{ //es un lugar ya registrado
            alimentoDao.udpateAlimento(alimento)
        }
    }

    suspend fun deleteAlimento(alimento: Alimento){
        if(alimento.id!=0) {//si un ID tiene un valor lo intento eliminar
            alimentoDao.deleteAlimento(alimento)
        }
    }

    val getAlimentos : LiveData<List<Alimento>> = alimentoDao.getAlimentos()
}