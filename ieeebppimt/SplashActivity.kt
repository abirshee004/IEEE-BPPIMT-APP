package com.example.ieeebppimt

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Make fullscreen (compatible with all Android versions)
        @Suppress("DEPRECATION")
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        // Start animated gradient background
        val animatedBackground: View = findViewById(R.id.animatedBackground)
        val animationDrawable = animatedBackground.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(1500)
        animationDrawable.setExitFadeDuration(1500)
        animationDrawable.start()

        // Delay for 3 seconds before opening RoleSelectionActivity
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, RoleSelectionActivity::class.java)
            startActivity(intent)
            finish() // Close splash screen
        }, 5000)
    }
}
