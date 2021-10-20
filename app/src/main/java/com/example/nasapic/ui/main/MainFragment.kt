package com.example.nasapic.ui.main

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.api.load
import com.example.nasapic.databinding.MainFragmentStartBinding
import com.example.nasapic.model.json_model.pic_of_the_day.PictureData

private const val WIKI_URL: String = "https://en.wikipedia.org/wiki/"

class MainFragment : Fragment() {
    private var _binding: MainFragmentStartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getData().observe(viewLifecycleOwner, { insertPictureOfTheDay(it) })

        binding.inputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(WIKI_URL + binding.inputEditText.text.toString())
            })
        }
    }

    private fun insertPictureOfTheDay(pictureData: PictureData?) {
        with(binding) {
            pictureData?.let {
                pictureTitle.text = it.title
                pictureImage.load(it.url)
            }
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}
