package co.paulfran.taskappv2.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.paulfran.taskappv2.R
import co.paulfran.taskappv2.models.Items

class ItemsViewHolder(inflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder(inflater.inflate(
    R.layout.item_row, parent, false)) {

    private var itemNameTextView: TextView? = null
    private var itemCheckBox: CheckBox? = null

    init {
        itemNameTextView = itemView.findViewById(R.id.item_name_tv)
        itemCheckBox = itemView.findViewById(R.id.item_checkbox)
    }

    fun bind(item: Items) {
        itemNameTextView!!.text = item.name
        itemCheckBox!!.isChecked = item.completed
    }

}