package com.rafidzz.firsttask.fragments

import android.os.Bundle
import android.view.View
 import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rafidzz.firsttask.R
import com.rafidzz.firsttask.adapter.ParentAdapter
import com.rafidzz.firsttask.datafactory.ParentDataFactory

class Category : Fragment(R.layout.fragment_category) {
    private lateinit var recyclerView: RecyclerView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        recyclerView = view.findViewById(R.id.recyclerView)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                RecyclerView.VERTICAL, false
            )
            adapter = ParentAdapter(
                ParentDataFactory
                    .getParents(20)
            )

        }

    }
}