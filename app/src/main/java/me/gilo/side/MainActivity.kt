package me.gilo.side

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import me.gilo.side.ui.home.HomeActivity
import me.gilo.side.ui.user.onboarding.LoginActivity
import me.gilo.side.ui.user.onboarding.OnboardingActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startActivity(Intent(baseContext, HomeActivity::class.java))
    }
}
