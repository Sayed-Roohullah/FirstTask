package com.rafidzz.firsttask.adapter

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.rafidzz.firsttask.R
import com.rafidzz.firsttask.`interface`.listClicklistener
import com.rafidzz.firsttask.fragments.HomeFragment
import com.rafidzz.firsttask.model.CharSelected

class sideAdapter(
    private val context: HomeFragment,
    private var list: List<CharSelected>,
    private val listClicklistener: listClicklistener
) : BaseAdapter() {

    override fun getViewTypeCount(): Int {
        return count
    }

    override fun getItemViewType(position: Int): Int {

        return position
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return list[position]

    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    private val selectedList = arrayListOf<String>()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val holder: ViewHolder
        val model = list[position]


        if (convertView == null) {
            holder = ViewHolder()
            val inflater = context.layoutInflater.inflate(R.layout.item_row, null, true)
            convertView = inflater

            holder.tvchar = convertView!!.findViewById(R.id.text_view) as TextView
            holder.view = convertView!!

            convertView.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
        }

        holder.tvchar!!.text = model.text
        holder.view!!.setBackgroundColor(if (model.isSelected) Color.CYAN else Color.WHITE)


        holder.view!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                model.isSelected = !model.isSelected
                if (model.isSelected) {
                    selectedList.add(model.text)
                    holder.view!!.setBackgroundColor(Color.CYAN)
                } else {
                    if (selectedList.contains(model.text))
                        selectedList.remove(model.text)
                    holder.view!!.setBackgroundColor(Color.WHITE)
                }
                listClicklistener.listclick(convertView, position, selectedList)

            }
        })



        return convertView

    }


    private inner class ViewHolder {

        var tvchar: TextView? = null
        var view: View? = null


    }


}