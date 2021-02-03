package co.paulfran.taskappv2.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Projects(
    @ColumnInfo(name="project_name")
    val name: String
    ) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}