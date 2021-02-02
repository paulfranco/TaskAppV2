package co.paulfran.taskappv2.activities
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import co.paulfran.taskappv2.adapters.ProjectAdapter
import co.paulfran.taskappv2.R
import co.paulfran.taskappv2.data.AppData
import co.paulfran.taskappv2.databinding.ActivityMainBinding
import co.paulfran.taskappv2.listeners.OnProjectClickedListener
import co.paulfran.taskappv2.models.Projects
import java.io.File
import java.lang.Exception


class MainActivity : BaseActivity(), OnProjectClickedListener {
    lateinit var binding: ActivityMainBinding
    private var projectsAdapter: ProjectAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.projectsRv.layoutManager = LinearLayoutManager(this)


        projectsAdapter = ProjectAdapter(AppData.projects, this)
        binding.projectsRv.adapter = projectsAdapter

        if (databaseFileExists()) {
            // Read content from db
        } else {
            // First time application is ran
            AppData.initialize()
            projectsAdapter = ProjectAdapter(AppData.projects, this)
            binding.projectsRv.adapter = projectsAdapter

            // Save the projects to Room
        }

    }

    override fun onResume() {
        super.onResume()
        projectsAdapter!!.notifyDataSetChanged()
    }

    fun createNewProject(v: View) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("New Project")
        builder.setMessage("Please enter a name for the new project")

        val input = EditText(this)
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)

        builder.setPositiveButton("Save") { _, _ ->
            val projectName: String = input.text.toString()
            val newProject = Projects(projectName, mutableListOf())

            AppData.projects.add(newProject)
            projectsAdapter!!.notifyItemInserted(AppData.projects.count())

        }

        builder.setNegativeButton("Cancel") { _, _ ->

        }

        val dialgue: AlertDialog = builder.create()
        dialgue.show()
    }

    override fun projectClicked(index: Int) {
        val intent = Intent(this, ItemsActivity::class.java)
        intent.putExtra("projectIndex", index)
        startActivity(intent)

        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    override fun projectLongClickedListener(index: Int) {
        // Delete group
        AppData.projects.removeAt(index)
        projectsAdapter!!.notifyItemRemoved(index)
    }

    private fun databaseFileExists(): Boolean {
        return try {
            File(getDatabasePath(AppData.dbFileName).absolutePath).exists()
        } catch (e: Exception) {
            return false
        }
    }
}



