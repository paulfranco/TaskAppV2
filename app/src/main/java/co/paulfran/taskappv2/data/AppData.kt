package co.paulfran.taskappv2.data

import co.paulfran.taskappv2.models.Item
import co.paulfran.taskappv2.models.Project

class AppData {

    companion object DataHolder {
        var projects: MutableList<Project> = mutableListOf()

        fun initialize() {
            val item1 = Item("Bread", false)
            val item2 = Item("Milk", true)

            val item3 = Item("Tap to cross", true)
            val item4 = Item("Long press to delete", false)

            val project1 = Project("Home", mutableListOf(item1, item2))
            val project2 = Project("Training", mutableListOf(item3, item4))

            projects = mutableListOf(project1, project2)
        }
    }
}