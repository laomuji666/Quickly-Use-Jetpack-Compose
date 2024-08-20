package com.laomuji666.compose.feature.hello

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.laomuji666.compose.core.ui.QuicklyTheme

@Composable
fun FirebaseScreen(
    viewModel: FirebaseViewModel = hiltViewModel()
){
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    FirebaseScreenUi(
        uiState = uiState,
        logEventClick = {
            viewModel.logEventClick()
        }
    )
}

@Composable
private fun FirebaseScreenUi(
    uiState: FirebaseUiState,
    logEventClick: () -> Unit = {},
){
    Scaffold {
        Column(modifier = Modifier.padding(it)) {
            Button(
                onClick = logEventClick,
                modifier = Modifier.fillMaxWidth().height(50.dp)
            ) {
                Text(text = "埋点")
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Preview
@Composable
fun PreviewFirebaseScreen(){
    QuicklyTheme {
        FirebaseScreenUi(
            uiState = FirebaseUiState()
        )
    }
}