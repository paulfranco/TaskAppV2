package co.paulfran.taskappv2.activities

import android.os.Build
import android.widget.TextView
import co.paulfran.taskappv2.R
import co.paulfran.taskappv2.data.AppData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


@Config(sdk = [Build.VERSION_CODES.O_MR1])
@RunWith(RobolectricTestRunner::class)
class MainActivityTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun fetchFirstProjectName() {
        // Given
        val activityController = Robolectric.buildActivity(MainActivity::class.java)
            // When
            .create()
            .resume()
            .visible()
        val systemUnderTest = activityController.get()
        val projectName = systemUnderTest.findViewById(R.id.project_title_tv) as TextView

        CoroutineScope(Dispatchers.IO).launch {
            val projects = AppData.db.projectDao().getProjectsWithItems()
            val project1Name = projects[0].project.name
            withContext(Dispatchers.Main) {
                // Then
                assertEquals(project1Name, projectName.text)
            }
        }
    }
}