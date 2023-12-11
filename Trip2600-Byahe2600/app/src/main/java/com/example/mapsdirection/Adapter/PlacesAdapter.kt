package com.example.mapsdirection.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mapsdirection.R
import com.example.mapsdirection.ViewHolder.PlaceViewHolder

class PlacesAdapter(private val placesList: List<PlaceViewHolder>) : RecyclerView.Adapter<PlacesAdapter.ViewHolderClass>() {
    var onItemClick: ((PlaceViewHolder) -> Unit)? = null

    inner class ViewHolderClass(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val placeName: TextView = itemView.findViewById(R.id.txtPlaceName)
        val placeAddress: TextView = itemView.findViewById(R.id.txtPlaceAddress)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            onItemClick?.invoke(placesList[adapterPosition])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.saved_item_layout, parent, false)
        return ViewHolderClass(view)
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val currentItem = placesList[position]
        holder.placeName.text = currentItem.place
        holder.placeAddress.text = currentItem.name
    }

    override fun getItemCount(): Int {
        return placesList.size
    }


}
