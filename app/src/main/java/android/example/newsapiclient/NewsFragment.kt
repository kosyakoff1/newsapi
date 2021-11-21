package android.example.newsapiclient

import android.example.newsapiclient.data.util.Resource
import android.example.newsapiclient.databinding.FragmentNewsBinding
import android.example.newsapiclient.presentation.adapter.NewsAdapter
import android.example.newsapiclient.presentation.viewmodel.NewsViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NewsFragment : Fragment() {

    //Better use this:
    // Use the 'by activityViewModels()' Kotlin property delegate
    // from the fragment-ktx artifact
    //private val model: SharedViewModel by activityViewModels()
    //TODO need to refactor paging and search
    private lateinit var viewModel: NewsViewModel

    private lateinit var fragmentNewsBinding: FragmentNewsBinding
    private lateinit var newsAdapter: NewsAdapter
    private var country = "us"
    private var page = 1

    private var isScrolling = false
    private var isLoading = false
    private var isLastPage = false
    private var pages = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentNewsBinding = FragmentNewsBinding.bind(view)
        viewModel = (activity as MainActivity).viewModel
        newsAdapter = (activity as MainActivity).newsAdapter
        newsAdapter.setOnItemClickListener {
            val action = NewsFragmentDirections.actionNewsFragmentToInfoFragment(it)
            findNavController().navigate(action)
        }
        initRecyclerView()
        viewNewsList()
        setSearchView()
    }

    private fun viewNewsList() {
        viewModel.getNewsHeadLines(country, page)
        viewModel.newsHeadLines.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        newsAdapter.differ.submitList(it.articles)

                        if (it.totalResults % 20 == 0) {
                            pages = it.totalResults / 20
                        } else {
                            pages = it.totalResults / 20 + 1
                        }

                        isLastPage = page == pages
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let {
                        Toast.makeText(
                            activity,
                            getString(R.string.txt_error_occurred, it),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }

        }
    }

    private fun initRecyclerView() {
        with(fragmentNewsBinding.rvNews) {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
            addOnScrollListener(onScrollListener)
        }
    }

    private fun showProgressBar(): FragmentNewsBinding {
        isLoading = true
        return fragmentNewsBinding.apply { progressBar.isVisible = true }
    }

    private fun hideProgressBar(): FragmentNewsBinding {
        isLoading = false
        return fragmentNewsBinding.apply { progressBar.isVisible = false }
    }

    private fun setSearchView() {
        fragmentNewsBinding.svNews.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                viewModel.getSearchHeadLines("us", p0.toString(), 1)
                viewSearchedNewsList()
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                MainScope().launch {
                    delay(2000)
                    viewModel.getSearchHeadLines("us", p0.toString(), 1)
                    viewSearchedNewsList()
                }
                return false
            }
        })

        fragmentNewsBinding.svNews.setOnCloseListener(SearchView.OnCloseListener {
            initRecyclerView()
            viewNewsList()
            false
        })
    }

    fun viewSearchedNewsList() {
        viewModel.searchedNews.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        newsAdapter.differ.submitList(it.articles)

                        if (it.totalResults % 20 == 0) {
                            pages = it.totalResults / 20
                        } else {
                            pages = it.totalResults / 20 + 1
                        }

                        isLastPage = page == pages
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let {
                        Toast.makeText(
                            activity,
                            getString(R.string.txt_error_occurred, it),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }

        }
    }

    private val onScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = fragmentNewsBinding.rvNews.layoutManager as LinearLayoutManager
            val sizeOfCurrentList = layoutManager.itemCount
            val visibleItems = layoutManager.childCount
            val topPosition = layoutManager.findFirstVisibleItemPosition()
            val hasReachedEnding = topPosition + visibleItems >= sizeOfCurrentList
            val shouldPaginate = !isLoading && !isLastPage && hasReachedEnding && isScrolling

            if (shouldPaginate) {
                page++
                viewModel.getNewsHeadLines(country, page)
                isScrolling = false
            }
        }

    }
}