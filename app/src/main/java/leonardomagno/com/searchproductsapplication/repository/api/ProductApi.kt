package leonardomagno.com.searchproductsapplication.repository.api

import leonardomagno.com.searchproductsapplication.models.ProductResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductApi {
    @GET("autocomplete")
    suspend fun search(
        @Query("content") content: String,
        @Query("source") source: String
    ): Response<ProductResponse>
}