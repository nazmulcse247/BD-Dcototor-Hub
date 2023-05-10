package com.homairaahmed.bddoctorhub.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity

class MapUtils {

    companion object {

        fun openMap(late : String , long : String,context: Context) {

            val gmmIntentUri = Uri.parse("geo:37.7749,-122.4194")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            mapIntent.resolveActivity(context.packageManager)?.let {
                context.startActivity(mapIntent)
            }

        }
    }
}