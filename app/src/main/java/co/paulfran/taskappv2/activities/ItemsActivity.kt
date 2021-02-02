package co.paulfran.taskappv2.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import co.paulfran.taskappv2.R
import co.paulfran.taskappv2.adapters.ItemsAdapter
import co.paulfran.taskappv2.data.AppData
import co.paulfran.taskappv2.databinding.ActivityItemsBinding

class ItemsActivity : AppCompatActivity() {

    lateinit var binding: ActivityItemsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_items)

        var selectedIndex = intent.getIntExtra("projectIndex", 0)
        var thisProject = AppData.projects[selectedIndex]

        binding.toolbarTitle.text = thisProject.name

        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        binding.itemsRv.layoutManager = LinearLayoutManager(this)
        var itemsAdapter = ItemsAdapter(thisProject)

        binding.itemsRv.adapter = itemsAdapter


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}