package com.messieyawo.myshopapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.messieyawo.myshopapp.R
import com.messieyawo.myshopapp.databinding.FragmentAccountOptionBinding


class AccountOptionFragment : Fragment() {
lateinit var binding:FragmentAccountOptionBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAccountOptionBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButton.setOnClickListener {
            val action = AccountOptionFragmentDirections.actionAccountOptionFragmentToLoginFragment()
            findNavController().navigate(action)
        }

        binding.registerButton.setOnClickListener {
            val action = AccountOptionFragmentDirections.actionAccountOptionFragmentToRegisterFragment()
            findNavController().navigate(action)
        }
    }
}