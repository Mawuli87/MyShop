package com.messieyawo.myshopapp.fragments.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.messieyawo.myshopapp.R
import com.messieyawo.myshopapp.databinding.FragmentMainCategoryBinding


class MainCategoryFragment : Fragment() {

lateinit var binding:FragmentMainCategoryBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMainCategoryBinding.inflate(inflater,container,false)
        return binding.root
    }


}