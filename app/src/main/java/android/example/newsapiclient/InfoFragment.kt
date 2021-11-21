package android.example.newsapiclient

import android.example.newsapiclient.databinding.FragmentInfoBinding
import android.example.newsapiclient.presentation.viewmodel.NewsViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

class InfoFragment : Fragment() {

    private lateinit var fragmentInfoBinding: FragmentInfoBinding
    private lateinit var viewModel: NewsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentInfoBinding = FragmentInfoBinding.bind(view)
        val article = InfoFragmentArgs.fromBundle(requireArguments()).selectedArticle
        fragmentInfoBinding.wvView.apply {
            webViewClient = WebViewClient()
            article.url.takeIf { it.isNotBlank() }?.let { loadUrl(article.url) }
        }

        viewModel = (activity as MainActivity).viewModel

        fragmentInfoBinding.fabSave.setOnClickListener {
            viewModel.saveArticle(article)
            Snackbar.make(view, getString(R.string.txt_saved_successfully), Snackbar.LENGTH_LONG)
                .show()
        }

    }
}