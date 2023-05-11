package com.homairaahmed.bddoctorhub.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity

class MapUtils {

    companion object {

        @SuppressLint("QueryPermissionsNeeded")
        fun openMap(late : String, long : String, context: Context) {
            var uriString = "google.navigation:q=$late,$long&mode=d"

            val gmmIntentUri = Uri.parse(uriString)
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            context.startActivity(mapIntent)

        }
    }
}