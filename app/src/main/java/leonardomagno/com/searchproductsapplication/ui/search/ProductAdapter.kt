package leonardomagno.com.searchproductsapplication.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import leonardomagno.com.searchproductsapplication.databinding.ItemProductViewHolderBinding
import leonardomagno.com.searchproductsapplication.models.Product

class ProductAdapter : ListAdapter<Product, RecyclerView.ViewHolder>(
    object : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean =
            oldItem == newItem
    }
) {

    private lateinit var itemProductViewHolderBinding: ItemProductViewHolderBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        itemProductViewHolderBinding = ItemProductViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(
            parent.context,
            itemProductViewHolderBinding
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let {
            (holder as? ProductViewHolder)?.bind(it)
        }
    }
}