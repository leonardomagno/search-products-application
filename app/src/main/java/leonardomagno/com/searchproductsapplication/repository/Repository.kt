package leonardomagno.com.searchproductsapplication.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import leonardomagno.com.searchproductsapplication.models.Product
import leonardomagno.com.searchproductsapplication.repository.api.ProductApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Repository {

    private const val BASE_URL = "https://mystique-v2-americanas.juno.b2w.io/"
    val productsAPI: ProductApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        productsAPI = retrofit.create(ProductApi::class.java)
    }

    suspend fun getSearchResults(
        content: String,
        source: String,
        onSucess: (RepositoriesList: List<Product>) -> Unit,
        onError: () -> Unit,
    ) {

        try {
            val response = productsAPI.search(content, source)
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null) {
                    withContext(Dispatchers.Main) {
                        onSucess.invoke(responseBody.products)
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        onError.invoke()
                    }
                }
            } else {
                withContext(Dispatchers.Main) {
                    onError.invoke()
                }
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                onError.invoke()
            }
        }
    }
}