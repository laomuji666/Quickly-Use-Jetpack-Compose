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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.laomuji666.compose.core.ui.QuicklyTheme

@Composable
fun HelloScreen(
    viewModel: HelloViewModel = hiltViewModel(),
    onFirebaseClick: ()->Unit
){
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    HelloScreenUi(
        uiState = uiState,
        onFirebaseClick =onFirebaseClick
    )
}

@Composable
private fun HelloScreenUi(
    uiState: HelloUiState,
    onFirebaseClick:()->Unit
){
    Scaffold {
        Column(
            modifier = Modifier.padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = uiState.helloText)
            HelloScreenSlot(
                text = "Firebase页面",
                onClick = onFirebaseClick
            )
        }
    }
}

@Composable
private fun HelloScreenSlot(
    text:String,
    onClick:()->Unit
){
    Column {
        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth().height(50.dp)
        ) {
            Text(text = text)
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}


@Preview
@Composable
fun PreviewHelloScreenUi(){
    QuicklyTheme {
        HelloScreenUi(
            uiState = HelloUiState(),
            onFirebaseClick = {}
        )
    }
}