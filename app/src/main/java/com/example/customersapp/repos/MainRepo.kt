package com.example.customersapp.repos

import android.content.SharedPreferences
import com.example.customersapp.Utils.Constants.KEY_TIME
import com.example.customersapp.pojo.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import javax.inject.Inject
import javax.inject.Named

class MainRepo @Inject constructor(
    var mAuth: FirebaseAuth,
    @Named("UserCollection")
    var UserRef: CollectionReference,
    var pref:SharedPreferences

) {

    fun logInUser(Email:String,Pass:String)=mAuth.signInWithEmailAndPassword(Email,Pass)
    fun signUpUser(Email: String,Pass: String)=mAuth.createUserWithEmailAndPassword(Email,Pass)
    fun addUserToDataBase(user: User)=UserRef.document(user.userId)
    fun addUserTimeToPref(Time: String)=pref.apply {
        edit().let { Editor->
            Editor.putString(KEY_TIME,Time)
            Editor.apply()
        }
    }
    fun getUserData(userId:String)= UserRef.document(userId)

    fun getAllUsers()=UserRef

}