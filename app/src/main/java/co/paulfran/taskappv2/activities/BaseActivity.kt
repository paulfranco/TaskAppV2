package co.paulfran.taskappv2.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.paulfran.taskappv2.R

open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }
}