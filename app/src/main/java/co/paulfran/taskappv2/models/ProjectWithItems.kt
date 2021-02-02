package co.paulfran.taskappv2.models

import androidx.room.Embedded
import androidx.room.Relation

data class ProjectWithItems(
    @Embedded
    val project: Projects,
    @Relation(parentColumn="project_name", entityColumn="project_name")
    val items: MutableList<Items>
    )