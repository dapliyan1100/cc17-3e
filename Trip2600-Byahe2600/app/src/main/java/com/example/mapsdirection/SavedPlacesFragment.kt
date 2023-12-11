package com.example.mapsdirection

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mapsdirection.Adapter.PlacesAdapter
import com.example.mapsdirection.ViewHolder.PlaceViewHolder
import org.json.JSONArray

class SavedPlacesFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var placesAdapter: PlacesAdapter
    private lateinit var sharedPreferences: SharedPreferences
    private var dataList : ArrayList<PlaceViewHolder> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_save_places, container, false)
        recyclerView = view.findViewById(R.id.savedRecyclerView)

        // Initialize SharedPreferences
        sharedPreferences = requireActivity().getSharedPreferences("Places", Context.MODE_PRIVATE)

        // Set up RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        placesAdapter = PlacesAdapter(dataList) // Retrieve saved places here
        recyclerView.adapter = placesAdapter

        getData()

        placesAdapter.onItemClick = {
            val intent = Intent(requireContext(), MainActivity::class.java)
            intent.putExtra("Android", it)
            startActivity(intent)
        }
        return view
    }

    private fun getData() {
        val placesString = sharedPreferences.getString("places", "[]") ?: "[]"
        val jsonArray = JSONArray(placesString)
        Log.d("SavedPlacesFragment", "JSON Array Length: ${jsonArray.length()}")
        dataList.clear()
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val placeName = jsonObject.getString("placeName")
            val placeLatLng = jsonObject.getString("latLng")
            val dataClass = PlaceViewHolder(placeName, placeLatLng)
            Log.d("SavedPlacesFragment", "JSON Array Length: ${jsonArray.length()}")
            dataList.add(dataClass)
        }
        placesAdapter.notifyDataSetChanged()
    }
}