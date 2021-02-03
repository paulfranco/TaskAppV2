package co.paulfran.taskappv2.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Items(
    @ColumnInfo(name="item_name")
    val name: String,
    @ColumnInfo(name="project_name")
    val projectName: String,
    var completed: Boolean
    ) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}