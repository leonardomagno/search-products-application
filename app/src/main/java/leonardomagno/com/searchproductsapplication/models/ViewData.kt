package leonardomagno.com.searchproductsapplication.models

import leonardomagno.com.searchproductsapplication.models.enums.ViewStatus

data class ViewData<D>(
    val data: D? = null,
    val viewStatus: ViewStatus
)
