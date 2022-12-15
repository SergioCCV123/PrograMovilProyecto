package com.mycalories.ui.restaurantes

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.mycalories.R
import com.mycalories.databinding.FragmentAddRestauranteBinding
import com.mycalories.model.Restaurante
import com.mycalories.viewmodel.RestauranteViewModel

class AddRestauranteFragment : Fragment() {

    private lateinit var restauranteViewModel: RestauranteViewModel
    private var _binding: FragmentAddRestauranteBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        restauranteViewModel =
            ViewModelProvider(this).get(RestauranteViewModel::class.java)

        _binding = FragmentAddRestauranteBinding.inflate(inflater,container,false)

        binding.btAdd.setOnClickListener { addRestaurante() }

        activaGPS()

        return binding.root
    }

    private fun activaGPS() {
        if(requireActivity()
                .checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)!=
            PackageManager.PERMISSION_GRANTED
            && requireActivity()
                .checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)!=
            PackageManager.PERMISSION_GRANTED) {
            requireActivity()
                .requestPermissions(
                    arrayOf(
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ), 105
                )
        }else{
            val ubicacion: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext())
            ubicacion.lastLocation.addOnSuccessListener { location: Location? ->
                if (location != null) {
                    binding.tvLatitud.text = "${location.latitude}"
                    binding.tvLongitud.text = "${location.longitude}"
                    binding.tvAltura.text = "${location.altitude}"
                } else {
                    binding.tvLatitud.text = "0.0"
                    binding.tvLongitud.text = "0.0"
                    binding.tvAltura.text = "0.0"
                }
            }
        }

    }

    private fun addRestaurante() {
        val nombre = binding.etNombre.text.toString()

        if(nombre.isNotEmpty()){
            val telefono = binding.etTelefono.text.toString()
            val altitud = binding.tvAltura.text.toString().toDouble()
            val longitud = binding.tvLongitud.text.toString().toDouble()
            val latitud = binding.tvLatitud.text.toString().toDouble()

            val restaurante = Restaurante(0,nombre,telefono, latitud, longitud, altitud)

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