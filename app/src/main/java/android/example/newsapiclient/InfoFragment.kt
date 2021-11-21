package android.example.newsapiclient

import android.example.newsapiclient.databinding.FragmentInfoBinding
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs

class InfoFragment : Fragment() {

    private lateinit var fragmentInfoBinding: FragmentInfoBinding
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

    }
}