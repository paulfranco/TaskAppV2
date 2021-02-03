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
import co.paulfran.taskappv2.models.Items
import co.paulfran.taskappv2.models.ProjectWithItems
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemsActivity : AppCompatActivity(), OnItemClickedListener {

    lateinit var binding: ActivityItemsBinding
    lateinit var projectWithItems: ProjectWithItems
    var itemsAdapter: ItemsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_items)

        val selectedIndex = intent.getIntExtra("projectIndex", 0)
        projectWithItems = AppData.projects[selectedIndex]

        binding.toolbarTitle.text = projectWithItems.project.name

        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        binding.itemsRv.layoutManager = LinearLayoutManager(this)
        itemsAdapter = ItemsAdapter(projectWithItems, this)

        binding.itemsRv.adapter = itemsAdapter

        binding.newItemEt.setOnKeyListener { view, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                if (event.action == KeyEvent.ACTION_DOWN) {
                    val name: String = binding.newItemEt.text.toString()
                    val item = Items(name, projectWithItems.project.name,false)

                    projectWithItems.items.add(item)

                    CoroutineScope(Dispatchers.IO).launch {
                        AppData.db.projectDao().insertItem(item)
                    }

                    itemsAdapter!!.notifyItemInserted(projectWithItems.items.count())
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

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    override fun itemClicked(index: Int) {
        val item = projectWithItems.items[index]
        item.completed = !(item.completed)

        val projectName = projectWithItems.project.name
        val itemName = projectWithItems.items[index].name

        CoroutineScope(Dispatchers.IO).launch {
            AppData.db.projectDao().updateItem(projectName, itemName, item.completed)
        }

        itemsAdapter!!.notifyDataSetChanged()
    }

    override fun itemLongClicked(index: Int) {
        val projectName = projectWithItems.project.name
        val itemName = projectWithItems.items[index].name

        CoroutineScope(Dispatchers.IO).launch {
            AppData.db.projectDao().deleteItem(projectName, itemName)
        }

        projectWithItems.items.removeAt(index)
        itemsAdapter!!.notifyItemRemoved(index)
    }
}