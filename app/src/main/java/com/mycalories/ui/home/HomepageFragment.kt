package com.mycalories.ui.home

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.mycalories.adapter.HomepageAdapter
import com.mycalories.databinding.FragmentHomepageBinding
import com.mycalories.viewmodel.HomepageViewModel

class HomepageFragment : Fragment() {

    private var _binding: FragmentHomepageBinding? = null
    private val binding get() = _binding!!
    private lateinit var homepageViewModel: HomepageViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        homepageViewModel =
            ViewModelProvider(this).get(HomepageViewModel::class.java)

        _binding = FragmentHomepageBinding.inflate(inflater,container,false)

        val homepageAdapter = HomepageAdapter()
        val reciclador = binding.reciclar
        reciclador.adapter = homepageAdapter

        reciclador.layoutManager = LinearLayoutManager(requireContext())
        homepageViewModel.getHomePage.observe(viewLifecycleOwner) { info ->
            homepageAdapter.setListaHomepage(info)
        }

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
        }

        if(requireActivity()
                .checkSelfPermission(Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED){
            requireActivity()
                .requestPermissions(
                    arrayOf(
                        Manifest.permission.CALL_PHONE
                    ), 105
                )
        }

        return binding.root
    }

}