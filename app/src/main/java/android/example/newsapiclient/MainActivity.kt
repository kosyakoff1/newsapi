package android.example.newsapiclient

import android.example.newsapiclient.databinding.ActivityMainBinding
import android.example.newsapiclient.presentation.viewmodel.NewsViewModel
import android.example.newsapiclient.presentation.viewmodel.NewsViewModelFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var factory: NewsViewModelFactory
    //better use  val model: MyViewModel by viewModels()
    lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navigation_fragment_container_view) as NavHostFragment
        binding.bnvNews.setupWithNavController(
            navHostFragment.navController
        )

        viewModel = ViewModelProvider(this, factory)[NewsViewModel::class.java]
    }
}