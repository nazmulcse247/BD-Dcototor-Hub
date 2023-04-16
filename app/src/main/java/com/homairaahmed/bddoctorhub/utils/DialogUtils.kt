package com.homairaahmed.bddoctorhub.utils

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.homairaahmed.bddoctorhub.R
import com.homairaahmed.bddoctorhub.databinding.NoInternetDialogBinding
import com.homairaahmed.bddoctorhub.interfaces.ResendRequestCallBack

class DialogUtils {

    companion object {

        fun customDialog(context: Context, resendRequestCallBack: ResendRequestCallBack){

            val dialog = AlertDialog.Builder(context , R.style.FullScreenWithStatusBar).create()
            val binding = NoInternetDialogBinding.inflate(LayoutInflater.from(context))
            dialog.setView(binding.root)
            binding.tryAgainBtn.setOnClickListener {
                resendRequestCallBack.resendRequest()
                dialog.dismiss()
            }
            dialog.setCanceledOnTouchOutside(false)
            dialog.show()
        }
    }
}