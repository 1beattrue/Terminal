package edu.mirea.onebeattrue.terminal.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import edu.mirea.onebeattrue.terminal.ui.theme.TerminalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TerminalTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel: TerminalViewModel = viewModel()
                    val screenState = viewModel.state.collectAsState()

                    when (val currentState = screenState.value) {
                        is TerminalScreenState.Content -> {
                            Log.d("MainActivity", "${currentState.barList}")
                            Terminal(bars = currentState.barList)
                        }

                        TerminalScreenState.Initial -> {}
                    }
                }
            }
        }
    }
}