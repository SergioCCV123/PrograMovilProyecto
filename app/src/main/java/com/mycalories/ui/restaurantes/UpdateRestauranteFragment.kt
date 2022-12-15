package com.mycalories.ui.restaurantes

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mycalories.R
import com.mycalories.databinding.FragmentUpdateRestauranteBinding
import com.mycalories.model.Restaurante
import com.mycalories.viewmodel.RestauranteViewModel

class UpdateRestauranteFragment : Fragment() {

    private val args by navArgs<UpdateRestauranteFragmentArgs>()
    private lateinit var  restauranteViewModel: RestauranteViewModel
    private var _binding : FragmentUpdateRestauranteBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        restauranteViewModel =
            ViewModelProvider(this).get(RestauranteViewModel::class.java)

        _binding = FragmentUpdateRestauranteBinding.inflate(inflater, container, false)

        binding.etNombre.setText(args.restaurante.nombre)
        binding.etTelefono.setText(args.restaurante.telefono)
        binding.tvAltura.setText(args.restaurante.altura.toString())
        binding.tvLatitud.setText(args.restaurante.latitud.toString())
        binding.tvLongitud.setText(args.restaurante.longitud.toString())

        binding.btUpdate.setOnClickListener{ updateRestaurante() }
        binding.btDelete.setOnClickListener{ deleteRestaurante() }
        binding.btPhone.setOnClickListener { makeCall() }

        return binding.root
    }

    private fun makeCall() {
        if(requireActivity()
                .checkSelfPermission(Manifest.permission.CALL_PHONE)!=
            PackageManager.PERMISSION_GRANTED){
            requireActivity()
                .requestPermissions(
                    arrayOf(Manifest.permission.CALL_PHONE), 105)
            makeCall()
        }else{
            val llamar = Intent(Intent.ACTION_CALL)
            llamar.data = Uri.parse("tel:"+ args.restaurante.telefono)
            startActivity(llamar)
        }
    }

    private fun deleteRestaurante() {
        val alerta = AlertDialog.Builder(requireContext())
        alerta.setTitle(R.string.bt_delete_Restaurante)
        alerta.setMessage(getString(R.string.msg_preguntaRestaurant_delete)+" ${args.restaurante.nombre}?")
        alerta.setPositiveButton(getString(R.string.msg_si)){_,_ ->
            restauranteViewModel.deleteRestaurante(args.restaurante)
            Toast.makeText(requireContext(),getString(R.string.msg_receta_deleted),Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateRestauranteFragment_to_nav_restaurantes)
        }
        alerta.setNegativeButton(getString(R.string.msg_no)){_,_ ->}
        alerta.create().show()
    }

    private fun updateRestaurante() {
        val nombre = binding.etNombre.text.toString()

        if(nombre.isNotEmpty()){
            val telefono = binding.etTelefono.text.toString()
            val altitud = binding.tvAltura.text.toString().toDouble()
            val longitud = binding.tvLongitud.text.toString().toDouble()
            val latitud = binding.tvLatitud.text.toString().toDouble()

            val restaurante = Restaurante(args.restaurante.id,nombre,telefono, latitud, longitud, altitud)

            restauranteViewModel.saveRestaurante(restaurante)

            Toast.makeText(requireContext(), getString(R.string.msg_restaurante_Added), Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_addRestauranteFragment_to_nav_restaurantes)
        }else{
            Toast.makeText(requireContext(), getString(R.string.msg_data), Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}