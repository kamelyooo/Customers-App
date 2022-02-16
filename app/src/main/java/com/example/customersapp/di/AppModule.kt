package com.example.customersapp.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

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



//    @Provides
//    @Singleton
//    fun provideSharedPref(
//        @ApplicationContext app:Context
//    )=app.getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE)
//
//    @Provides
//    @Singleton
//    @Named("UserName")
//    fun ProvideName(
//        sharedPref:SharedPreferences
//    )=sharedPref.getString(KEY_NAME,"")?:""
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
//    @Provides
//    @Singleton
//    fun provideFireBaseAuth()= FirebaseAuth.getInstance()
//
//    @Provides
//    @Singleton
//    @Named("UserCollection")
//    fun provideFireBaseStoreUser()= Firebase.firestore.collection("Users")
//    @Provides
//    @Singleton
//    @Named("RidesCollection")
//    fun provideFireBaseRids()= Firebase.firestore.collection("Rides")
//
//    @Provides
//    @Singleton
//    fun provideFireBaseImageStorage() = FirebaseStorage.getInstance().reference
//
//
//    @Provides
//    @Singleton
//    fun provideFireBaseDataBase() = FirebaseDatabase.getInstance().reference.child("supports")
}