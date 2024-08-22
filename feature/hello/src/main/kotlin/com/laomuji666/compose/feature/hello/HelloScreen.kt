package com.laomuji666.compose.feature.hello

import android.widget.Toast
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.laomuji666.compose.core.logic.authenticate.GoogleAuthenticate
import com.laomuji666.compose.core.ui.QuicklyTheme

@Composable
fun HelloScreen(
    viewModel: HelloViewModel = hiltViewModel(),
    onFirebaseClick: ()->Unit,
    onHttpClick:()->Unit
){
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    HelloScreenUi(
        uiState = uiState,
        onFirebaseClick = onFirebaseClick,
        onHttpClick = onHttpClick,
        onGoogleLoginClick = {
            GoogleAuthenticate().requestLogin(
                coroutineScope = coroutineScope,
                activityContext = context,
                onSuccess = { email, idToken ->
                    Toast.makeText(context,"$email : $idToken",Toast.LENGTH_LONG).show()
                },
                onFail = {
                    Toast.makeText(context,"登录失败",Toast.LENGTH_SHORT).show()
                }
            )
        }
    )
}

@Composable
private fun HelloScreenUi(
    uiState: HelloUiState,
    onFirebaseClick:()->Unit,
    onHttpClick:()->Unit,
    onGoogleLoginClick:()->Unit
){
    Scaffold {
        Column(
            modifier = Modifier.padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = uiState.helloText)
            Spacer(modifier = Modifier.height(10.dp))
            HelloScreenSlot(
                text = "Firebase例子",
                onClick = onFirebaseClick
            )
            HelloScreenSlot(
                text = "Http例子",
                onClick = onHttpClick
            )
            HelloScreenSlot(
                text = "谷歌登录",
                onClick = onGoogleLoginClick
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
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
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
            onFirebaseClick = {},
            onHttpClick = {},
            onGoogleLoginClick = {}
        )
    }
}