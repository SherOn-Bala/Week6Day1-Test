package ca.judacribz.week6day1_test.model

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

data class Coffee(
    @SerializedName("last_updated_at")
    var lastUpdatedAt: String? = "",
    @SerializedName("desc")
    var desc: String? = "",
    @SerializedName("id")
    var id: String? = "",
    @SerializedName("image_url")
    var imageUrl: String? = "",
    @SerializedName("name")
    var name: String? = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(lastUpdatedAt)
        parcel.writeString(desc)
        parcel.writeString(id)
        parcel.writeString(imageUrl)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Coffee> {
        override fun createFromParcel(parcel: Parcel): Coffee {
            return Coffee(parcel)
        }

        override fun newArray(size: Int): Array<Coffee?> {
            return arrayOfNulls(size)
        }
    }
}