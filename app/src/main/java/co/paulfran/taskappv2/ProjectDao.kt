package co.paulfran.taskappv2

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import co.paulfran.taskappv2.models.Items
import co.paulfran.taskappv2.models.ProjectWithItems
import co.paulfran.taskappv2.models.Projects

@Dao
interface ProjectDao {

    // Insert a project
    @Insert
    suspend fun insertProject(project: Projects)

    // Insert an item
    @Insert
    suspend fun insertItem(item: Items)

    // Get all Projects and items
    @Transaction @Query("SELECT * FROM Projects")
    suspend fun getProjectsWithItems(): MutableList<ProjectWithItems>

    // Get items of a project
    @Query("SELECT * FROM Items WHERE project_name=:projectName")
    suspend fun getItemOfProject(projectName: String): MutableList<Items>

    // Delete a project
    @Query("DELETE FROM Projects WHERE project_name=:projectName")
    suspend fun deleteProject(projectName: String)

    // Delete an item
    @Query("DELETE FROM Items WHERE project_name=:projectName AND item_name=:itemName")
    suspend fun deleteItem(projectName: String, itemName: String)

    // Delete items of project
    @Query("DELETE FROM Items WHERE project_name=:projectName")
    suspend fun deleteItemsOfProject(projectName: String)

    // Update an item
    @Query("UPDATE Items SET completed=:completedVal WHERE item_name=:itemName AND project_name=:projectName")
    suspend fun updateItem(projectName: String, itemName: String, completedVal: Boolean)

}