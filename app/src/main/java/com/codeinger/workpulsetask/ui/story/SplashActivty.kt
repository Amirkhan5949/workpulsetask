package com.codeinger.workpulsetask.ui.story

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.codeinger.workpulsetask.databinding.ActivitySplashActivtyBinding


class SplashActivty : AppCompatActivity() {
    private lateinit var binding : ActivitySplashActivtyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        binding= ActivitySplashActivtyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler().postDelayed(Runnable { /* Create an Intent that will start the Menu-Activity. */
            val mainIntent = Intent(this, MainActivity::class.java)
           this.startActivity(mainIntent)
            this.finish()
        }, 1000)


    }
}