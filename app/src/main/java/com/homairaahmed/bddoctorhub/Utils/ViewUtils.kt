package com.homairaahmed.bddoctorhub.Utils

import android.app.Activity
import android.content.Intent
import com.homairaahmed.bddoctorhub.R

class ViewUtils {


    companion object {

        public fun START_ACTIVITY_WITH_ANIMATION(activity : Activity,intent : Intent) {
            activity.startActivity(intent)
            activity.overridePendingTransition(R.anim.animation_slide_in_right, R.anim.animation_slide_out_left)

        }

    }
}