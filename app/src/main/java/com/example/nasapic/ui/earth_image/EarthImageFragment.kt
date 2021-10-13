package com.example.nasapic.ui.earth_image

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import com.example.nasapic.databinding.FragmentEarthImageBinding
import com.example.nasapic.model.json_model.earth_image.EarthImageData

class EarthImageFragment : Fragment() {
    private var _binding: FragmentEarthImageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEarthImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this).get(EarthImageViewModel::class.java)
        with(binding) {
            earthImageButton.setOnClickListener {
                viewModel.getData(
                    lonText.text.toString(),
                    latText.text.toString(),
                    dateText.text.toString(),
                    dimText.text.toString()
                ).observe(viewLifecycleOwner, { insertEarthImage(it) })
            }
        }
    }

    private fun insertEarthImage(earthImageData: EarthImageData?) {
        with(binding) {
            earthImageData?.let {
                earthImage.load(it.url)
            }
        }
    }
}