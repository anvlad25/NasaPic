package com.example.nasapic.ui.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.nasapic.R
import com.example.nasapic.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initElem()

    }

    private fun initElem() {
        binding.radioButton1.setOnCheckedChangeListener { compoundButton, isChecked ->
            (
                    if (isChecked) {
                        context?.theme?.applyStyle(R.style.NewStyle, true)
                        childFragmentManager
                            .beginTransaction()
                            //.replace(R.id.container, SettingsFragment())
                            //.detach(this)
                            //.attach(this)
                            .commit()
                        Toast.makeText(
                            context,
                            "${binding.radioButton1.isChecked} 1",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    )
        }
        binding.radioButton2.setOnCheckedChangeListener { compoundButton, isChecked ->
            (
                    if (isChecked) {
                        context?.theme?.applyStyle(R.style.Theme_NasaPic, true)
                        childFragmentManager
                            .beginTransaction()
                            //.replace(R.id.container, SettingsFragment())
                            //.detach(this)
                            //.attach(this)
                            .commit()
                        Toast.makeText(
                            context,
                            "${binding.radioButton2.isChecked} 2",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    )
        }
    }
}