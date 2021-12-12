package leonardomagno.com.searchproductsapplication.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import leonardomagno.com.searchproductsapplication.R
import leonardomagno.com.searchproductsapplication.ui.search.SearchActivity

class SplashActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        splashTransition()
    }

    private fun splashTransition() {
        val intent = Intent(this@SplashActivity, SearchActivity::class.java)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(intent)
            finish()
        }, 3000)
    }
}