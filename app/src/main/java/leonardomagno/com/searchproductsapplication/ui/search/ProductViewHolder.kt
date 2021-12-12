package leonardomagno.com.searchproductsapplication.ui.search

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import leonardomagno.com.searchproductsapplication.databinding.ItemProductViewHolderBinding
import leonardomagno.com.searchproductsapplication.models.Product

class ProductViewHolder(
    private val context: Context,
    itemProductViewHolderBinding: ItemProductViewHolderBinding
) : RecyclerView.ViewHolder(itemProductViewHolderBinding.root) {

    private val viewHolderTextViewProductName = itemProductViewHolderBinding.itemProductTextViewName

    fun bind(product: Product) {
        viewHolderTextViewProductName.text = product.name
    }
}
