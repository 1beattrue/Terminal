package edu.mirea.onebeattrue.terminal.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.mirea.onebeattrue.terminal.data.ApiFactory
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TerminalViewModel : ViewModel() {
    private val apiService = ApiFactory.apiService

    private val _state = MutableStateFlow<TerminalScreenState>(TerminalScreenState.Initial)
    val state = _state.asStateFlow()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _state.value = TerminalScreenState.Failure
        Log.d("ExceptionHandler", "Exception caught: $throwable")
    }

    init {
        loadBarList()
    }

    fun loadBarList(
        timeFrame: TimeFrame = TimeFrame.HOUR_1
    ) {
        viewModelScope.launch(exceptionHandler) {
            _state.value = TerminalScreenState.Loading
            val barList = apiService.loadBars(timeFrame.value).barList
            _state.value = TerminalScreenState.Content(barList, timeFrame)
        }
    }
}