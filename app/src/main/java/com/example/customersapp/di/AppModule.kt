package com.example.customersapp.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.customersapp.Utils.Constants.KEY_TIME
import com.example.customersapp.Utils.Constants.SHARED_PREF_NAME
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {



    @Provides
    @Singleton
    fun provideSharedPref(
        @ApplicationContext app:Context
    )=app.getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE)

    @Provides
    @Singleton
    fun ProvideName(
        sharedPref:SharedPreferences
    )=sharedPref.getString(KEY_TIME,"")?:""
//    @Provides
//    @Singleton
//    @Named("UserImage")
//    fun ProvideUserImage(
//        sharedPref:SharedPreferences
//    )=sharedPref.getString(KEY_Image,"")?:""
//
//    @Provides
//    @Singleton
//    fun ProvideWeight(
//        sharedPref:SharedPreferences
//    )=sharedPref.getFloat(KET_WEIGHT,80f)
//
////
////    @Provides
////    @Singleton
////    @Named("isDDemo")
////    fun ProvideFristTimeToggle(
////        sharedPref:SharedPreferences
////    )=sharedPref.getBoolean(KEY_FIRST_TIME_TOGGLE,true)
//
    @Provides
    @Singleton
    fun provideFireBaseAuth()= FirebaseAuth.getInstance()

    @Provides
    @Singleton
    @Named("UserCollection")
    fun provideFireBaseStoreUser()= Firebase.firestore.collection("Users")

}