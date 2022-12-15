package com.mycalories.ui.restaurantes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mycalories.R
import com.mycalories.adapter.RestauranteAdapter
import com.mycalories.databinding.FragmentRestaurantesBinding
import com.mycalories.viewmodel.RestauranteViewModel

class RestaurantesFragment : Fragment() {

    private var _binding : FragmentRestaurantesBinding? = null
    private val binding get() = _binding!!
    private lateinit var restauranteViewModel: RestauranteViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        restauranteViewModel =
            ViewModelProvider(this)[RestauranteViewModel::class.java]

        _binding = FragmentRestaurantesBinding.inflate(inflater, container, false)

        binding.addRestauranteFabButton.setOnClickListener{
            findNavController().navigate(R.id.action_nav_restaurantes_to_addRestauranteFragment)
        }

        val restauranteAdapter = RestauranteAdapter()
        val reciclador = binding.Reclicador
        reciclador.adapter = restauranteAdapter

        reciclador.layoutManager = LinearLayoutManager(requireContext())
        restauranteViewModel.getRestaurante.observe(viewLifecycleOwner) {restaurantes ->
            restauranteAdapter.setListaAlimentos(restaurantes)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}