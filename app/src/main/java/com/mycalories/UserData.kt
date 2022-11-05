package com.mycalories

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.mycalories.databinding.ActivityUserDataBinding
import com.mycalories.model.Usuario
import com.mycalories.viewmodel.UsuarioViewModel

class UserData : AppCompatActivity() {

    private lateinit var binding: ActivityUserDataBinding
    private lateinit var usuarioViewModel: UsuarioViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        usuarioViewModel = ViewModelProvider(this).get(UsuarioViewModel::class.java)
        binding = ActivityUserDataBinding.inflate(layoutInflater)
        binding.btnAccept.setOnClickListener { addUsuario() }
        setContentView(binding.root)
    }

    private fun addUsuario() {
        val uid = FirebaseAuth.getInstance().currentUser!!.uid
        if(uid!=null){
            val nombre = binding.fieldNombre.text.toString()
            val telefono = binding.fieldTelefono.text.toString()
            val altura = (binding.fieldAltura.text.toString()).toDouble()
            val peso = (binding.fieldPeso.text.toString()).toDouble()
            val bmi = peso/(altura*altura)
            val usuario = Usuario(uid, nombre, telefono, altura, peso, bmi)

            usuarioViewModel.saveUsuario(usuario)
            Toast.makeText(baseContext, "Usuario Agregado", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, PrincipalActivity::class.java)
            startActivity(intent)
        }else{
            Toast.makeText(baseContext, "ERROR", Toast.LENGTH_LONG).show()
        }
    }

    public override fun onStart(){
        super.onStart()
    }
}