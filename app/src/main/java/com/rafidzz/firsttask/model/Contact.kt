package com.rafidzz.firsttask.model

class Contact {


        var name: String? = null
        var number: String? = null


        fun setNames(name: String) {
            this.name = name
        }

        fun getNumbers(): String {
            return number.toString()
        }

        fun setNumbers(number: String) {
            this.number = number
        }

        fun getNames(): String {
            return name.toString()
        }
}