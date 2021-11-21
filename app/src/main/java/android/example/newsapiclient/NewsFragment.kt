package android.example.newsapiclient

import android.example.newsapiclient.data.util.Resource
import android.example.newsapiclient.databinding.FragmentNewsBinding
import android.example.newsapiclient.presentation.adapter.NewsAdapter
import android.example.newsapiclient.presentation.viewmodel.NewsViewModel
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager

class NewsFragment : Fragment() {

    //Better use this:
    // Use the 'by activityViewModels()' Kotlin property delegate
    // from the fragment-ktx artifact
    //private val model: SharedViewModel by activityViewModels()
    private lateinit var viewModel: NewsViewModel

    private lateinit var fragmentNewsBinding: FragmentNewsBinding
    private lateinit var newsAdapter: NewsAdapter
    private var country = "us"
    private var page = 1

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
        initRecyclerView()
        viewNewsList()
    }

    private fun viewNewsList() {
        viewModel.getNewsHeadLines(country, page)
        viewModel.newsHeadLines.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        newsAdapter.differ.submitList(it.articles)
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

    private fun initRecyclerView() = with(fragmentNewsBinding.rvNews) {
        newsAdapter = NewsAdapter()
        adapter = newsAdapter
        layoutManager = LinearLayoutManager(activity)
    }

    private fun showProgressBar() = fragmentNewsBinding.apply { progressBar.isVisible = true }

    private fun hideProgressBar() =
        fragmentNewsBinding.apply { progressBar.isVisible = false }
}