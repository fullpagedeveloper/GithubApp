package com.fullpagedeveloper.githubuserapp

import android.animation.Animator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.fullpagedeveloper.githubuserapp.ui.search.UsersActivity
import kotlinx.android.synthetic.main.activity_splas.*

class SplasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splas)

        splash_Lottie.addAnimatorListener(object : Animator.AnimatorListener{
            override fun onAnimationStart(p0: Animator?) {}

            override fun onAnimationEnd(p0: Animator?) {
                Handler(Looper.getMainLooper()).postDelayed({
                    startActivity(Intent(this@SplasActivity, UsersActivity::class.java))
                    finish()
                },2000)
            }

            override fun onAnimationCancel(p0: Animator?) {}

            override fun onAnimationRepeat(p0: Animator?) {}

        })
    }
}