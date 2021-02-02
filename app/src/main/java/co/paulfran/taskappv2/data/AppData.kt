package co.paulfran.taskappv2.data

import co.paulfran.taskappv2.ProjectDatabase
import co.paulfran.taskappv2.models.Items
import co.paulfran.taskappv2.models.ProjectWithItems
import co.paulfran.taskappv2.models.Projects

class AppData {

    companion object DataHolder {
        var dbFileName = "project_db"
        lateinit var db: ProjectDatabase

        var projects: MutableList<ProjectWithItems> = mutableListOf()

        fun initialize() {
            val project1 = Projects("Home")
            val item1 = Items("Bread", project1.name, false)
            val item2 = Items("Milk", project1.name, true)

            val projectWithItems1 = ProjectWithItems(project1, mutableListOf(item1, item2))

            val project2 = Projects("Training")
            val item3 = Items("Tap to cross", project2.name,true)
            val item4 = Items("Long press to delete", project2.name, false)

            val projectWithItems2 = ProjectWithItems(project2, mutableListOf(item3, item4))


            projects = mutableListOf(projectWithItems1, projectWithItems2)
        }
    }
}