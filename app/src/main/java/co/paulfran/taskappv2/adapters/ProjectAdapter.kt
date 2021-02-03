package co.paulfran.taskappv2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.paulfran.taskappv2.listeners.OnProjectClickedListener
import co.paulfran.taskappv2.models.ProjectWithItems
import co.paulfran.taskappv2.viewholder.ProjectsViewHolder
import co.paulfran.taskappv2.models.Projects

class ProjectAdapter(private val list: List<ProjectWithItems>, listenerContext: OnProjectClickedListener): RecyclerView.Adapter<ProjectsViewHolder>() {

    private var projectListener: OnProjectClickedListener = listenerContext

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ProjectsViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ProjectsViewHolder, position: Int) {
        val projectWithItems = list[position]
        holder.bind(projectWithItems)

        holder.itemView.setOnClickListener {
            projectListener.projectClicked(position)
        }

        holder.itemView.setOnLongClickListener {
            projectListener.projectLongClickedListener(position)
            true
        }
    }

    override fun getItemCount(): Int = list.size

}