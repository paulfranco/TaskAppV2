package co.paulfran.taskappv2.activities



import android.os.Bundle
import androidx.databinding.DataBindingUtil
import co.paulfran.taskappv2.R
import co.paulfran.taskappv2.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

    }
}