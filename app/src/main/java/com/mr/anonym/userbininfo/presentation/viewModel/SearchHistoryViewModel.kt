package com.mr.anonym.userbininfo.presentation.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mr.anonym.domain.model.SearchHistoryEntity
import com.mr.anonym.domain.useCases.local.LocalUseCases
import com.mr.anonym.userbininfo.presentation.utils.BinState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchHistoryViewModel @Inject constructor(
    private val localUseCases: LocalUseCases
) :ViewModel() {

    private val _searchHistories = mutableStateOf( BinState().histories )
    val searchHistories: State<List<SearchHistoryEntity>> = _searchHistories

    init {
        getAllHistory()
    }

    private fun getAllHistory() = viewModelScope.launch{
        localUseCases.getAllSearchHistory.execute().collect{
            _searchHistories.value = it
        }
    }

    fun clearHistory(histories:List<SearchHistoryEntity>) = viewModelScope.launch {
        localUseCases.clearSearchHistory.execute(histories)
    }
}