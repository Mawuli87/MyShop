package com.messieyawo.myshopapp.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.messieyawo.myshopapp.R
import com.messieyawo.myshopapp.activities.ShoppingActivity
import com.messieyawo.myshopapp.databinding.FragmentLoginBinding
import com.messieyawo.myshopapp.dialog.setupBottomSheetDialog
import com.messieyawo.myshopapp.resource.Resource
import com.messieyawo.myshopapp.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class LoginFragment : Fragment() {

lateinit var binding:FragmentLoginBinding
private val viewModel by viewModels<LoginViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvDontHaveAnAccount.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.tvForgotPassword.setOnClickListener {
            setupBottomSheetDialog { email ->
            viewModel.resetPassword(email)
            }
        }
        //subscribing and getting reset live data from
        lifecycleScope.launchWhenCreated {
           viewModel.resetPassword.collect {
               when(it){
                   is Resource.Loading -> {

                   }
                   is Resource.Success ->{
                     Snackbar.make(requireView(),"Reset link has been sent to you",Snackbar.LENGTH_LONG).show()
                   }
                   is Resource.Error -> {
                       Snackbar.make(requireView(),"Error ${it.message}",Snackbar.LENGTH_LONG).show()
                   }
                   else -> Unit
               }
           }
        }


        binding.apply {
          btnLoginFragment.setOnClickListener {
              val email = edEmailLogin.text.toString().trim()
              val password = edPasswordLogin.text.toString()
              viewModel.login(email,password)
          }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.login.collect{
                when(it){
                    is Resource.Loading -> {
                     binding.btnLoginFragment.startAnimation()
                    }
                    is Resource.Success -> {
                      binding.btnLoginFragment.revertAnimation()
                        Intent(requireActivity(),ShoppingActivity::class.java).also {intent ->
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                        }
                    }
                    is Resource.Error -> {
                        binding.btnLoginFragment.revertAnimation()
                     Toast.makeText(requireContext(),it.message,Toast.LENGTH_LONG).show()

                    }
                    else -> Unit
                }
            }
        }
    }



}