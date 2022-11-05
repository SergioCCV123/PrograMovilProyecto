package com.mycalories.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
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

        return binding.root
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