package com.mycalories.repository

import androidx.lifecycle.LiveData
import com.example.recetas.data.RecetaDao
import com.mycalories.model.Receta

class RecetaRepository(private val recetaDao: RecetaDao){

    suspend fun saveReceta(receta: Receta){
        if(receta.id==0){//es una receta nueva
            recetaDao.addReceta(receta)
        }else{ //es una receta ya registrado
            recetaDao.udpateReceta(receta)
        }
    }

    suspend fun deleteReceta(receta: Receta){
        if(receta.id!=0) {//si un ID tiene un valor lo intento eliminar
            recetaDao.deleteReceta(receta)
        }
    }

    val getRecetas : LiveData<List<Receta>> = recetaDao.getRecetas()
}