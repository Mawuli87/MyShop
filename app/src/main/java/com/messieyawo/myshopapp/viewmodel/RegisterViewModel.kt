package com.messieyawo.myshopapp.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.messieyawo.myshopapp.utils.Constants.Companion.USERS_COLLECTION
import com.messieyawo.com.myshopapplication.utils.RegisterFieldState
import com.messieyawo.com.myshopapplication.utils.RegisterValidation
import com.messieyawo.com.myshopapplication.utils.validateEmail
import com.messieyawo.com.myshopapplication.utils.validatePassword
import com.messieyawo.myshopapp.data.User
import com.messieyawo.myshopapp.resource.Resource

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
  private val firebaseAuth: FirebaseAuth,
  private val db: FirebaseFirestore
):ViewModel() {
    private val _register = MutableStateFlow<Resource<User>>(Resource.Unspecified())
    val register: Flow<Resource<User>> = _register

    private val _validation = Channel<RegisterFieldState>()
     val validation = _validation.receiveAsFlow()

    fun createAccountWithEmailAndPassword(user: User, password:String) {
        if (CheckValidation(user, password)) {

            runBlocking {
                _register.emit(Resource.Loading())
            }
            firebaseAuth.createUserWithEmailAndPassword(user.email, password)
                .addOnSuccessListener {
                    it.user.let {
                       // _register.value = Resource.Success(it)
                        saveUserInfo(it!!.uid,user)
                    }
                }.addOnFailureListener {
                    _register.value = Resource.Error(it.message.toString())
                }
        }else {
            val registerFieldState = RegisterFieldState(
                validateEmail(user.email), validatePassword(password)
            )
            runBlocking {
                _validation.send(registerFieldState)
            }
        }
    }

    private fun saveUserInfo(userUid: String, user: User) {
     db.collection(USERS_COLLECTION)
         .document(userUid)
         .set(user)
         .addOnSuccessListener {
              _register.value = Resource.Success(user)
         }.addOnFailureListener {
             _register.value = Resource.Error(it.message.toString())
         }
    }

    private fun CheckValidation(user: User, password: String):Boolean {
        val emailValidation = validateEmail(user.email)
        val passwordValidation = validatePassword(password)
        val shouldRegister = emailValidation is RegisterValidation.Success &&
                passwordValidation is RegisterValidation.Success

        return shouldRegister
    }
}