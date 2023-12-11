package com.example.mapsdirection.ViewHolder

import android.os.Parcel
import android.os.Parcelable

data class PlaceViewHolder(
    var place :String,
    var name :String
): Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(place)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PlaceViewHolder> {
        override fun createFromParcel(parcel: Parcel): PlaceViewHolder {
            return PlaceViewHolder(parcel)
        }

        override fun newArray(size: Int): Array<PlaceViewHolder?> {
            return arrayOfNulls(size)
        }
    }

}