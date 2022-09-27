package com.rafidzz.firsttask.fragments


import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import android.widget.ListView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.SearchView
import androidx.cardview.widget.CardView
import androidx.core.view.get
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.rafidzz.firsttask.R
import com.rafidzz.firsttask.`interface`.listClicklistener
import com.rafidzz.firsttask.adapter.ContactAdapter
import com.rafidzz.firsttask.adapter.sideAdapter
import com.rafidzz.firsttask.model.CharSelected
import com.rafidzz.firsttask.model.Contact
import java.util.*
import kotlin.collections.ArrayList


class HomeFragment : Fragment(R.layout.fragment_home), listClicklistener {
    private lateinit var searchView: SearchView

    private lateinit var sidelist: ListView
    private lateinit var sideAdapter: sideAdapter

    private var listView: ListView? = null
    private lateinit var customAdapter: ContactAdapter
    private lateinit var contactlist: ArrayList<Contact>

    private var listData = ArrayList<Contact>()
    var completeContactlist = ArrayList<Contact>()


    private lateinit var addContact: CardView

    var data = ArrayList<CharSelected>()
    val alphabaticalList = arrayOf(
        "All", "A", "B", "C", "D", "E",
        "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
        "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchView = view.findViewById(R.id.searchview)
        listView = view.findViewById(R.id.listView) as ListView
        sidelist = view.findViewById(R.id.sideIndex)

        customAdapter = ContactAdapter(this)


        sideAdapter = sideAdapter(this, getListData(), this)
        sidelist.adapter = sideAdapter


        fetchContact()

        addContact = view.findViewById(R.id.addcontact)
        addContact.setOnClickListener {
            addContact()
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filter(newText)
                return false
            }
        })

    }

    override fun onResume() {
        super.onResume()
        customAdapter.update(contactlist)

    }

    private fun getListData(): ArrayList<CharSelected> {
        data = ArrayList()
        for (i in alphabaticalList) {
            data.add(CharSelected(i))
        }
        return data
    }

    @SuppressLint("Range")
    private fun fetchContact() {
        contactlist = ArrayList()
        val resolver = requireActivity().contentResolver
        val phones = resolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC"
        )
        while (phones!!.moveToNext()) {
            val name =
                phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
            val phoneNumber =
                phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))

            val contactModel = Contact()
            contactModel.setNames(name)
            contactModel.setNumbers(phoneNumber)
            contactlist.add(contactModel)
            Log.d("name>>", name + "  " + phoneNumber)
        }
        phones.close()

        customAdapter.setSelectedItemsList(contactlist)
        listView?.adapter = customAdapter
        completeContactlist.addAll(contactlist)


    }

    private fun addContact() {

        val contactIntent = Intent(ContactsContract.Intents.Insert.ACTION)
        contactIntent.type = ContactsContract.RawContacts.CONTENT_TYPE

        contactIntent
            .putExtra(ContactsContract.Intents.Insert.NAME, " ")
            .putExtra(ContactsContract.Intents.Insert.PHONE, "0 ")
        resultLauncher.launch(contactIntent)
    }

    var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // There are no request codes
                val data: Intent? = result.data

            }
            if (result.resultCode == Activity.RESULT_CANCELED) {
                fetchContact()

            }


        }

    private fun filter(text: String) {
        val filteredlist: ArrayList<Contact> = ArrayList<Contact>()
        for (item in contactlist) {

            if (item.getNames().lowercase().contains(text.lowercase(Locale.getDefault()))) {
                filteredlist.add(item)
            }
            if (item.getNames().uppercase().contains(text.uppercase(Locale.getDefault()))) {
                filteredlist.add(item)
            }
            if (item.getNumbers().contains(text)) {
                filteredlist.add(item)
            }
        }
        if (filteredlist.isEmpty()) {
            listView?.isVisible = false
            Toast.makeText(context, "No Data Found..", Toast.LENGTH_SHORT).show()
        } else {
            listView?.isVisible = true

            customAdapter.filterList(filteredlist)
        }
    }

    override fun listclick(v: View?, position: Int, chList: List<String>) {

        listData.clear()
        if (chList.isEmpty() || position == 0) {
            listData = ArrayList(completeContactlist)
        } else {
            completeContactlist.forEach { it
               val templist = completeContactlist.filter { it1 ->
                    chList.any {
                        it.startsWith(it1.name!!.first())
                    }
                }
                listData = ArrayList(templist)
            }
        }
        customAdapter.setSelectedItemsList(listData)


    }


}
