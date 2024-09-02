package com.techtool.remoting.UI

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.techtool.remoting.R
import com.techtool.remoting.adapter.explore_adapter
import com.techtool.remoting.adapter.internship_adapter
import com.techtool.remoting.data.explore_data
import com.techtool.remoting.data.internship_data


class explore : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: explore_adapter
    private lateinit var explore_items: MutableList<explore_data>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_explore, container, false)
        recyclerView = view.findViewById(R.id.recyeclerview_explore)




        explore_items = mutableListOf<explore_data>()


        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager


        adapter = explore_adapter(requireContext(), explore_items)
        recyclerView.adapter = adapter
        // Retrieve data from Firebase and populate the adapter
        val databaseReference = FirebaseDatabase.getInstance().reference.child("explore")
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                explore_items.clear()
                for (itemSnapshot in snapshot.children) {
                    val item = itemSnapshot.getValue(explore_data::class.java)
                    item?.let { explore_items.add(it) }
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