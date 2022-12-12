package com.mycalories.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.mycalories.PrincipalActivity
import com.mycalories.databinding.FragmentProfileBinding
import com.mycalories.model.Usuario
import com.mycalories.viewmodel.UsuarioViewModel

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var usuarioViewModel: UsuarioViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        usuarioViewModel =
            ViewModelProvider(this).get(UsuarioViewModel::class.java)

        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        dibuja(usuarioViewModel.getUsuario)

        binding.btnReturn.setOnClickListener {
            updateProfile()
        }

        return binding.root
    }

    private fun updateProfile() {
        val uid = FirebaseAuth.getInstance().currentUser!!.uid
        if(uid!=null){
            val nombre = binding.fieldNombre.text.toString()
            val telefono = binding.fieldTelefono.text.toString()
            val altura = (binding.fieldAltura.text.toString()).toDouble()
            val peso = (binding.fieldPeso.text.toString()).toDouble()
            val bmi = peso/(altura*altura)
            val usuario = Usuario(uid, nombre, telefono, altura, peso, bmi)

            usuarioViewModel.saveUsuario(usuario)
            Toast.makeText(requireContext(), "Usuario Actualizado", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(requireContext(), "ERROR", Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun dibuja(usuario: Usuario){
        if(usuario!=null){
            binding.fieldNombre.setText(usuario.nombre, null)
            binding.fieldTelefono.setText(usuario.telefono, null)
            binding.fieldAltura.setText(usuario.altura.toString(), null)
            binding.fieldPeso.setText(usuario.peso.toString(), null)
            binding.fieldBMI.setText(usuario.bmi.toString(), null)
        }
    }
}