package com.techtool.remoting.UI

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.techtool.remoting.R
import com.techtool.remoting.adapter.internship_adapter
import com.techtool.remoting.adapter.recommanded_adapter
import com.techtool.remoting.data.internship_data
import com.techtool.remoting.data.recommanded_data
import androidx.appcompat.widget.SearchView


class internship : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: internship_adapter
    private lateinit var internshipitems: MutableList<internship_data>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_internship, container, false)
        recyclerView = view.findViewById(R.id.recyclerviewinternship)




        internshipitems = mutableListOf<internship_data>()


        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager

        adapter = internship_adapter(requireContext(), internshipitems)
        recyclerView.adapter = adapter
        // Retrieve data from Firebase and populate the adapter
        val databaseReference = FirebaseDatabase.getInstance().reference.child("internship")
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                internshipitems.clear()
                for (itemSnapshot in snapshot.children) {
                    val item = itemSnapshot.getValue(internship_data::class.java)
                    item?.let { internshipitems.add(it) }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("HomeFragment", "Failed to read value.", error.toException())
            }
        })





        return view
    }


}