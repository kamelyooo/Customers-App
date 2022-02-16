package com.example.customersapp.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.customersapp.pojo.User
import com.example.customersapp.repos.MainRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    var repo: MainRepo
) : ViewModel() {
    private var errorMsg = ""
    private val isLoginSuccess = MutableLiveData<Boolean>()

    fun loginUser(Email: String, Pass: String) = viewModelScope.launch(Dispatchers.IO) {
        repo.logInUser(Email, Pass)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    it.result?.user?.uid?.let { id ->
                        getUserData(id)
                    }


                } else {
                    isLoginSuccess.postValue(false)
                    it.exception?.let { exp ->
                        errorMsg = exp.message.toString()
                    }
                }


            }.addOnFailureListener {
                isLoginSuccess.postValue(false)
                errorMsg = it.message.toString()
            }
    }

    private fun getUserData(userId: String) {
        repo.getUserData(userId).get().addOnCompleteListener {
            if (it.isSuccessful) {
                val user = it.result?.toObject(User::class.java)
                user?.let {
                    repo.addUserTimeToPref(user.userTimeAdded)
                }
                isLoginSuccess.postValue(true)

            } else if (!it.isSuccessful) {
                isLoginSuccess.postValue(false)
                it.exception?.let { exp ->
                    errorMsg = exp.message.toString()
                }
            }
        }.addOnFailureListener {
            isLoginSuccess.postValue(false)
            it.let { exp ->
                errorMsg = exp.message.toString()
            }
        }
    }
    fun getErrorMsg()=errorMsg
    fun getIsLoginSuccess()=isLoginSuccess
}