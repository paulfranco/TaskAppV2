package co.paulfran.taskappv2.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.paulfran.taskappv2.R
import co.paulfran.taskappv2.models.ProjectWithItems
import co.paulfran.taskappv2.models.Projects

class ProjectsViewHolder(inflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder(inflater.inflate(
    R.layout.project_row, parent, false)) {
    private var projectNameTextView: TextView? = null
    private var projectCountTextView: TextView? = null

    init {
        projectNameTextView = itemView.findViewById(R.id.project_title_tv)
        projectCountTextView = itemView.findViewById(R.id.project_count_tv)
    }

    fun bind(projectWithItems: ProjectWithItems) {
        projectNameTextView!!.text = projectWithItems.project.name
        projectCountTextView!!.text = "${projectWithItems.items.count()} items"
    }
}