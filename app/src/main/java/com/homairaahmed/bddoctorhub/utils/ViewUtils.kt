package com.homairaahmed.bddoctorhub.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import com.homairaahmed.bddoctorhub.R
import com.homairaahmed.bddoctorhub.databinding.CustomToastLayoutBinding

class ViewUtils {


    companion object {

        fun START_ACTIVITY_WITH_ANIMATION(activity : Activity,intent : Intent) {
            activity.startActivity(intent)
            activity.overridePendingTransition(R.anim.animation_slide_in_right, R.anim.animation_slide_out_left)

        }

        fun SHOW_TOAST(context : Context , message : String, toastType : String) {

            lateinit var binding : CustomToastLayoutBinding
            binding = CustomToastLayoutBinding.inflate(LayoutInflater.from(context))
        }



    }
}