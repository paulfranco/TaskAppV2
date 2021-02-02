package co.paulfran.taskappv2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.paulfran.taskappv2.listeners.OnItemClickedListener
import co.paulfran.taskappv2.models.Item
import co.paulfran.taskappv2.models.Project
import co.paulfran.taskappv2.viewholder.ItemsViewHolder

class ItemsAdapter(private val project: Project, listenerContext: OnItemClickedListener): RecyclerView.Adapter<ItemsViewHolder>() {

    private var itemsListener: OnItemClickedListener = listenerContext

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ItemsViewHolder(inflater, parent)

    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        val item: Item = project.items[position]
        holder.bind(item)

        holder.itemView.setOnClickListener {
            itemsListener.itemClicked(position)
        }

        holder.itemView.setOnLongClickListener {
            itemsListener.itemLongClicked(position)
            true
        }

    }

    override fun getItemCount(): Int = project.items.size

}