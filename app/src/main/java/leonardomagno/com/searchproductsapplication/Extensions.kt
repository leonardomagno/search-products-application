package leonardomagno.com.searchproductsapplication

import android.view.View

fun View?.visible() {
    this?.apply {
        visibility = View.VISIBLE
    }
}
fun View?.gone() {
    this?.apply {
        visibility = View.GONE
    }
}
fun View?.invisible() {
    this?.apply {
        visibility = View.INVISIBLE
    }
}