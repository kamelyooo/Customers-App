package com.example.customersapp.viewModels

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.customersapp.pojo.User
import com.example.customersapp.repos.MainRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(

    var repo: MainRepo

) : ViewModel() {
    private var errorMsg = ""
    private val isSignUpSuccess = MutableLiveData<Boolean>()
    fun sighUpUser(Email: String, Pass: String, timeAdded: String, username: String) =
        viewModelScope.launch(Dispatchers.IO) {
            repo.signUpUser(Email, Pass)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        it.result?.let {
                            addUserToDataBase(
                                user = User(
                                    userId = it.user?.uid.toString(),
                                    usrName = username,
                                    userTimeAdded = timeAdded
                                )
                            )
                        }

                    } else if (!it.isSuccessful) {
                        it.exception?.let {
                            isSignUpSuccess.postValue(false)
                            errorMsg = it.message.toString()
                        }
                    }
                }
                .addOnFailureListener {
                    isSignUpSuccess.postValue(false)
                    errorMsg = it.message.toString()
                }
        }

    fun addUserToDataBase(user: User) = CoroutineScope(Dispatchers.IO).launch {
        repo.addUserToDataBase(user)
            .set(user).addOnCompleteListener {
                if (it.isSuccessful) {
                    isSignUpSuccess.postValue(true)
                    repo.addUserTimeToPref(user.userTimeAdded)
                } else if (!it.isSuccessful) {
                    it.exception?.let {
                        isSignUpSuccess.postValue(false)
                        errorMsg = it.message.toString()
                    }
                }
            }.addOnFailureListener {
                isSignUpSuccess.postValue(false)
                errorMsg = it.message.toString()
            }
    }

    fun getErrorMsg()=errorMsg
    fun isSignUpSuccess()=isSignUpSuccess
}