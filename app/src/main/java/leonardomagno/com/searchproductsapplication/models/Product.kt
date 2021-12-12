package leonardomagno.com.searchproductsapplication.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id: String,
    val name: String
): Parcelable