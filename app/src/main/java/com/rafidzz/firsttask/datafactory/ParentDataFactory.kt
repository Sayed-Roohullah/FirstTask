package com.rafidzz.firsttask.datafactory

import com.rafidzz.firsttask.model.ChildModel
import com.rafidzz.firsttask.model.PModel
import java.util.*

object ParentDataFactory{
    private val random = Random()

    private val titles =  arrayListOf( "Now Playing", "Classic", "Comedy", "Thriller", "Action", "Horror", "TV Series")

    private fun randomTitle() : String{
        val index = random.nextInt(titles.size)
        return titles[index]
    }

    private fun randomChildren() : List<ChildModel>{
        return ChildDataFactory.getChildren(12)
    }

    fun getParents(count : Int) : List<PModel>{
        val parents = mutableListOf<PModel>()
        repeat(count){
            val parent = PModel(randomTitle(), randomChildren())
            parents.add(parent)
        }
        return parents
    }
}