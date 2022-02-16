package com.example.customersapp

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

import com.google.android.material.dialog.MaterialAlertDialogBuilder

class LogOutAlertDialog :DialogFragment() {
    private var yesListener:(()->Unit)?=null

    fun setYesListener(listener :(()->Unit)){
        yesListener=listener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return MaterialAlertDialogBuilder(requireContext())
            .setTitle("LogOut?")
            .setMessage("Are You Sure To LogOut")
            .setIcon(R.drawable.ic_baseline_exit_to_app_24)
            .setPositiveButton("yes"){_,_->
                yesListener?.let {yes->
                   yes()
                }
            }
            .setNegativeButton("No"){DialogInterface,_->
                DialogInterface.cancel()

            }.create()
    }
}