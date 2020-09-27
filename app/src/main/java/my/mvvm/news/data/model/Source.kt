package my.mvvm.news.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Source(
    @Expose
    @SerializedName("id")
    val sourceId: String?,
    @Expose
    @SerializedName("name")
    val sourceName: String?
) : Parcelable