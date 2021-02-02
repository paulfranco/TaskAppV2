package co.paulfran.taskappv2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import co.paulfran.taskappv2.databinding.ActivityItemsBinding

class ItemsActivity : AppCompatActivity() {

    lateinit var binding: ActivityItemsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_items)

    }
}