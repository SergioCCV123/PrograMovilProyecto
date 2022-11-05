package com.mycalories.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mycalories.data.UsuarioDatabase
import com.mycalories.model.Usuario
import com.mycalories.repository.UsuarioRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UsuarioViewModel (application: Application) : AndroidViewModel(application){

    private val usuarioRepository : UsuarioRepository
    val getUsuario : Usuario

    init{
        val usuarioDao = UsuarioDatabase.getDatabase(application).usuarioDao()
        usuarioRepository = UsuarioRepository(usuarioDao)
        getUsuario = usuarioRepository.getUsuario(FirebaseAuth.getInstance().currentUser!!.uid)
    }

    fun saveUsuario (usuario: Usuario){
        viewModelScope.launch(Dispatchers.IO){
            usuarioRepository.saveUsuario(usuario)
        }
    }



}