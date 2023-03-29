package com.homairaahmed.bddoctorhub.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.genius.multiprogressbar.MultiProgressBar
import com.homairaahmed.bddoctorhub.R
import com.homairaahmed.bddoctorhub.Utils.ViewUtils
import com.homairaahmed.bddoctorhub.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    private lateinit var multiProgressBar: MultiProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        multiProgressBar = findViewById<MultiProgressBar>(R.id.m_progress_bar)

        multiProgressBar.start()

        multiProgressBar.setFinishListener(object : MultiProgressBar.ProgressFinishListener{
            override fun onProgressFinished() {
                ViewUtils.START_ACTIVITY_WITH_ANIMATION(this@SplashActivity, Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            }

        })

    }



}