package co.paulfran.taskappv2.activities

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import co.paulfran.taskappv2.R
import co.paulfran.taskappv2.adapters.ItemsAdapter
import co.paulfran.taskappv2.data.AppData
import co.paulfran.taskappv2.databinding.ActivityItemsBinding
import co.paulfran.taskappv2.listeners.OnItemClickedListener
import co.paulfran.taskappv2.models.Item
import co.paulfran.taskappv2.models.Project

class ItemsActivity : AppCompatActivity(), OnItemClickedListener {

    lateinit var binding: ActivityItemsBinding
    lateinit var thisProject: Project
    var itemsAdapter: ItemsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_items)

        val selectedIndex = intent.getIntExtra("projectIndex", 0)
        thisProject = AppData.projects[selectedIndex]

        binding.toolbarTitle.text = thisProject.name

        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        binding.itemsRv.layoutManager = LinearLayoutManager(this)
        itemsAdapter = ItemsAdapter(thisProject, this)

        binding.itemsRv.adapter = itemsAdapter

        binding.newItemEt.setOnKeyListener { view, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                if (event.action == KeyEvent.ACTION_DOWN) {
                    val name: String = binding.newItemEt.text.toString()
                    val item = Item(name, false)
                    thisProject.items.add(item)
                    itemsAdapter!!.notifyItemInserted(thisProject.items.count())
                    binding.newItemEt.text.clear()

                    // hide keyboard
                    val inputManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputManager.hideSoftInputFromWindow(view.windowToken, 0)
                }
            }
            false
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun itemClicked(index: Int) {
        thisProject.items[index].completed = !thisProject.items[index].completed
        itemsAdapter!!.notifyDataSetChanged()
    }

    override fun itemLongClicked(index: Int) {
        thisProject.items.removeAt(index)
        itemsAdapter!!.notifyItemRemoved(index)
    }
}