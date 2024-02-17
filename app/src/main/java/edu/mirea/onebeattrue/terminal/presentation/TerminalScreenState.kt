package edu.mirea.onebeattrue.terminal.presentation

import edu.mirea.onebeattrue.terminal.data.Bar

sealed class TerminalScreenState {
    object Initial : TerminalScreenState()
    data class Content(val barList: List<Bar>) : TerminalScreenState()
}
