package com.rafidzz.firsttask.adapter
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.rafidzz.firsttask.R
//import com.rafidzz.firsttask.model.ChildModel
//
//class ItemAdapter(list: ArrayList<ChildModel>) :
//    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//
//    companion object {
//        const val VIEW_TYPE_ONE = 1
//        const val VIEW_TYPE_TWO = 2
//    }
//
//     var list: ArrayList<ChildModel> = list
//    private inner class View1ViewHolder(itemView: View) :
//        RecyclerView.ViewHolder(itemView) {
//        var message: TextView = itemView.findViewById(R.id.textView)
//        fun bind(position: Int) {
//            val recyclerViewModel = list[position]
//            message.text = recyclerViewModel.textData
//        }
//    }
//
//    private inner class View2ViewHolder(itemView: View) :
//        RecyclerView.ViewHolder(itemView) {
//        var message: TextView = itemView.findViewById(R.id.textView)
//        fun bind(position: Int) {
//            val recyclerViewModel = list[position]
//            message.text = recyclerViewModel.textData
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//         if (viewType == VIEW_TYPE_ONE) {
//            return View1ViewHolder(
//                LayoutInflater.from(parent.context).inflate(R.layout.parent_recycler, parent, false)
//            )
//        }
//        return View2ViewHolder(
//            LayoutInflater.from(parent.context).inflate(R.layout.child_recycler, parent, false)
//        )
//    }
//
//    override fun getItemCount(): Int {
//        return list.size
//    }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//
//        if (list[position].viewType == VIEW_TYPE_ONE) {
//            (holder as View1ViewHolder).bind(position)
//         } else {
//            (holder as View2ViewHolder).bind(position)
//        }
//    }
//
//    override fun getItemViewType(position: Int): Int {
//        return list[position].viewType
//    }
//}