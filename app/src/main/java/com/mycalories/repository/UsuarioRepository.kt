package com.mycalories.repository

import androidx.lifecycle.LiveData
import com.mycalories.data.UsuarioDao
import com.mycalories.model.Usuario

class UsuarioRepository (private val usuarioDao: UsuarioDao){

    suspend fun saveUsuario(usuario: Usuario){
        if(usuario.uid!=null){
            usuarioDao.addUsuario(usuario)
        }
    }

    fun getUsuario(user : String): Usuario = usuarioDao.getUser(user)

}