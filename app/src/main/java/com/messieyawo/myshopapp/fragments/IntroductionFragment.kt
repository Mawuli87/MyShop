package com.messieyawo.myshopapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController

import com.messieyawo.myshopapp.R
import com.messieyawo.myshopapp.databinding.FragmentIntroductionBinding

class IntroductionFragment : Fragment() {
  lateinit var binding: FragmentIntroductionBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentIntroductionBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnFirstscreen.setOnClickListener {
             val action = IntroductionFragmentDirections.actionIntroductionFragmentToAccountOptionFragment()
             findNavController().navigate(action)
        }
    }

}