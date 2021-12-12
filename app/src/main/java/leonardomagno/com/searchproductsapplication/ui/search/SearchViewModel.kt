package leonardomagno.com.searchproductsapplication.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import leonardomagno.com.searchproductsapplication.models.Product
import leonardomagno.com.searchproductsapplication.models.ViewData
import leonardomagno.com.searchproductsapplication.models.enums.ViewStatus
import leonardomagno.com.searchproductsapplication.repository.Repository

class SearchViewModel : ViewModel() {

    val liveDataRepository = MutableLiveData<ViewData<List<Product>>>()
    val repository = Repository
    val JOB_TIMEOUT = 900L

    fun searchResults(content: String, source: String) {

        liveDataRepository.value = ViewData(viewStatus = ViewStatus.LOADING)
        CoroutineScope(IO).launch {
            withTimeout(JOB_TIMEOUT) {
                repository.getSearchResults(content, source,
                    onSucess = {
                        liveDataRepository.value = ViewData(data = it,
                            viewStatus = ViewStatus.SUCESS)
                    },
                    onError = {
                        liveDataRepository.value = ViewData(viewStatus = ViewStatus.ERROR)
                    }
                )
            }
        }
    }
}