package me.gilo.side.ui.user.onboarding

import android.content.Context
import android.os.Bundle
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import me.gilo.side.R
import me.gilo.side.common.BaseActivity


class SignInActivity : BaseActivity() {


    private var TAG = "SignUpActivity"


    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "Sign In"


    }




}
