package com.rafidzz.firsttask.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.VISIBLE
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.rafidzz.firsttask.R
import com.rafidzz.firsttask.fragments.HomeFragment
import com.rafidzz.firsttask.model.Contact


class ContactAdapter(private val context: HomeFragment) : BaseAdapter() {

    private var selectedItemsList: ArrayList<Contact> = ArrayList()
    fun setSelectedItemsList(selectedItemsList: ArrayList<Contact>) {
        this.selectedItemsList = selectedItemsList
        notifyDataSetChanged()
    }

    override fun getViewTypeCount(): Int {
        return count
    }

    override fun getItemViewType(position: Int): Int {

        return position
    }

    override fun getCount(): Int {
        return selectedItemsList.size
    }

    override fun getItem(position: Int): Any {
        return selectedItemsList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val holder: ViewHolder

        if (convertView == null) {
            holder = ViewHolder()
            val inflater = context.layoutInflater.inflate(R.layout.list_item, null, true)
            convertView = inflater

            holder.tvcard = convertView.findViewById(R.id.cardview) as CardView
            holder.tvname = convertView.findViewById(R.id.name) as TextView
            holder.tvnumber = convertView.findViewById(R.id.number) as TextView
            holder.tvoption = convertView.findViewById(R.id.option) as ImageView

            convertView.tag = holder
        } else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = convertView.tag as ViewHolder
        }

        holder.tvname.setText(selectedItemsList[position].getNames())
        holder.tvnumber.setText(selectedItemsList[position].getNumbers())

        holder.tvcard.setOnLongClickListener {
            holder.tvoption.visibility = VISIBLE
            holder.tvoption.setOnClickListener {
                selectedItemsList.removeAt(position)
                notifyDataSetChanged()
            }
            return@setOnLongClickListener true
        }


        return convertView!!
    }

    fun update(list: ArrayList<Contact>) {
        this.selectedItemsList = list
        notifyDataSetChanged()

    }

    fun filterList(filterlist: ArrayList<Contact>) {
        this.selectedItemsList = filterlist
        notifyDataSetChanged()
    }


    private inner class ViewHolder {

        lateinit var tvcard: CardView
        lateinit var tvname: TextView
        lateinit var tvnumber: TextView
        lateinit var tvoption: ImageView

    }
}