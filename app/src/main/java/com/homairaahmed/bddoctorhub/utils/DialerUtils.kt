package com.homairaahmed.bddoctorhub.utils

import android.content.Context
import android.content.Intent
import android.net.Uri

class DialerUtils {

    companion object {

        fun dailNumber(context : Context, number : String) {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$number"))
            context.startActivity(intent)
        }
    }
}