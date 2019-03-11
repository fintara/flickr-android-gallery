package com.tsovedenski.flickrgallery.domain.models

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Tsvetan Ovedenski on 10/03/19.
 */
data class FlickrPhoto (
    val title: String,
    val date: String,
    val tags: String,
    val thumbUrl: String,
    val imageUrl: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(date)
        parcel.writeString(tags)
        parcel.writeString(thumbUrl)
        parcel.writeString(imageUrl)
    }

    override fun describeContents(): Int {
        return hashCode()
    }

    companion object CREATOR : Parcelable.Creator<FlickrPhoto> {
        override fun createFromParcel(parcel: Parcel): FlickrPhoto {
            return FlickrPhoto(parcel)
        }

        override fun newArray(size: Int): Array<FlickrPhoto?> {
            return arrayOfNulls(size)
        }
    }
}