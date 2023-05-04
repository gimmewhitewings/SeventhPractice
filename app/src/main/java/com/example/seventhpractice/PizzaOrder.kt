package com.example.seventhpractice

import android.os.Parcel
import android.os.Parcelable

class PizzaOrder(
    var date: String = "", var flavor: String = "", var quantity: Int = 0
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!, parcel.readString()!!, parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(date)
        parcel.writeString(flavor)
        parcel.writeInt(quantity)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PizzaOrder> {
        override fun createFromParcel(parcel: Parcel): PizzaOrder {
            return PizzaOrder(parcel)
        }

        override fun newArray(size: Int): Array<PizzaOrder?> {
            return arrayOfNulls(size)
        }
    }

}