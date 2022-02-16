package com.example.customersapp.viewModels

import android.util.Log
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
class HomeViewModel @Inject constructor(
    var repo: MainRepo,
    var timeAdded: String
) : ViewModel() {


    private var usersList=MutableLiveData<List<User>>()
    fun getAllUsers()=viewModelScope.launch(Dispatchers.IO) {
        Log.i("xxxTimeAdded",timeAdded)
        repo.getAllUsers().orderBy("userTimeAdded")
            .whereGreaterThan("userTimeAdded",timeAdded).addSnapshotListener { value, error ->
            error?.let {
                return@let
            }
            value?.let {
                usersList.postValue(it.toObjects(User::class.java))
            }
        }
    }
     fun getUsersList()=usersList
}