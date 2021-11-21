package android.example.newsapiclient.presentation.viewmodel

import android.app.Application
import android.example.newsapiclient.domain.GetNewsHeadlinesUseCase
import android.example.newsapiclient.domain.GetSearchedNewsUseCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class NewsViewModelFactory(
    private val app: Application,
    private val getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase,
    private val getSearchedNewsUseCase: GetSearchedNewsUseCase,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(app, getNewsHeadlinesUseCase, getSearchedNewsUseCase) as T
    }
}