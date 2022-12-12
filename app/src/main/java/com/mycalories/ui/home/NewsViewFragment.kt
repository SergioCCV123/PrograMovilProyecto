package com.mycalories.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.mycalories.databinding.FragmentNewsViewBinding
import com.mycalories.viewmodel.HomepageViewModel

class NewsViewFragment : Fragment() {

    private var _binding: FragmentNewsViewBinding? = null
    private val binding get() = _binding!!
    private lateinit var homepageViewModel: HomepageViewModel

    private val args by navArgs<NewsViewFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        homepageViewModel =
            ViewModelProvider(this).get(HomepageViewModel::class.java)

        _binding = FragmentNewsViewBinding.inflate(inflater, container, false)

        binding.tvTitulo.setText(args.homepage.Titulo)
        binding.tvText.setText(args.homepage.Texto)


        if(args.homepage.rutaImagen?.isNotEmpty()==true){
            Glide.with(requireContext())
                .load(args.homepage.rutaImagen)
                .fitCenter()
                .into(binding.tvImagen)
        }


        return binding.root
    }

}