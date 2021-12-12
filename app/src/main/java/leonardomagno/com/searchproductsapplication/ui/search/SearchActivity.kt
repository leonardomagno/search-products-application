package leonardomagno.com.searchproductsapplication.ui.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import leonardomagno.com.searchproductsapplication.R
import leonardomagno.com.searchproductsapplication.databinding.ActivitySearchBinding
import leonardomagno.com.searchproductsapplication.gone
import leonardomagno.com.searchproductsapplication.models.Product
import leonardomagno.com.searchproductsapplication.models.ViewData
import leonardomagno.com.searchproductsapplication.models.enums.ViewStatus
import leonardomagno.com.searchproductsapplication.ui.dialogs.ErrorDialog
import leonardomagno.com.searchproductsapplication.visible

class SearchActivity : AppCompatActivity() {
    private val productAdapter = ProductAdapter()

    private lateinit var searchBinding: ActivitySearchBinding
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var viewLoading: View
    private lateinit var recyclerView: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager

    private val source = "nanook"
    private var dataList = ArrayList<Product>()
    private var isRecycleLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchBinding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(searchBinding.root)

        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        setupRecyclerView()
        setupViews()
        applyObservers()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return searchAction(query, searchView)
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })
        return true
    }

    private fun searchAction(
        query: String?,
        searchView: SearchView
    ): Boolean {
        if (query != null) {
            dataList.clear()
            recyclerView.scrollToPosition(0)
            searchViewModel.searchResults(query, source)
            searchView.clearFocus()
        }
        return true
    }

    private fun applyObservers() {
        searchViewModel.liveDataRepository.observe(this, {viewData ->
            when(viewData.viewStatus) {
                ViewStatus.SUCESS -> {
                    sucessCaseSetup(viewData)
                }

                ViewStatus.LOADING -> {
                    viewLoading.visible()
                }

                ViewStatus.ERROR -> {
                    errorCaseSetup()
                }
            }
        })
    }

    private fun errorCaseSetup() {
        isRecycleLoading = false
        ErrorDialog(this).callErrorDialog()
        viewLoading.gone()
    }

    private fun sucessCaseSetup(viewData: ViewData<List<Product>>) {
        isRecycleLoading = false
        viewLoading.gone()
        dataList.addAll(viewData.data ?: emptyList())
        if (!dataList.isNullOrEmpty()) {
            productAdapter.submitList(dataList)
            productAdapter.notifyDataSetChanged()
        }
    }

    private fun setupRecyclerView() {
        recyclerView = searchBinding.activitySearchRecyclerView
        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = productAdapter
        }
    }

    private fun setupViews() {
        viewLoading = searchBinding.activitySearchLoadingView.root
    }
}