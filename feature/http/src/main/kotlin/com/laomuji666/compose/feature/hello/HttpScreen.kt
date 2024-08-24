package com.laomuji666.compose.feature.hello

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.laomuji666.compose.core.ui.view.ErrorView
import com.laomuji666.compose.core.ui.view.LoadingDialog
import com.laomuji666.compose.core.ui.theme.QuicklyTheme
import com.laomuji666.compose.res.R

@Composable
fun HttpScreen(
    viewModel: HttpViewModel = hiltViewModel()
){
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LoadingDialog(uiState.isLoading)
    if(uiState.isError){
        ErrorView {
            HttpScreen(
                viewModel = hiltViewModel(key = uniqueKey())
            )
        }
        return
    }

    HttpScreenUi(
        onClickSendGet = {
            viewModel.getListUsers()
        },
        onClickSendPost = {
            viewModel.createUser()
        }
    )
}

@Composable
private fun HttpScreenUi(
    onClickSendGet:()->Unit,
    onClickSendPost:()->Unit
){
    Scaffold {
        Column(
            modifier = Modifier.padding(it),
            verticalArrangement = Arrangement.Center
        ) {
            HttpScreenSlot(
                text = stringResource(id = R.string.string_http_screen_get_demo),
                onClick = onClickSendGet
            )
            HttpScreenSlot(
                text = stringResource(id = R.string.string_http_screen_post_demo),
                onClick = onClickSendPost
            )
        }
    }
}

@Composable
private fun HttpScreenSlot(
    text:String,
    onClick:()->Unit
){
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

@Preview
@Composable
fun PreviewHttpScreen(){
    QuicklyTheme {
        HttpScreenUi(
            onClickSendGet = {},
            onClickSendPost = {}
        )
    }
}

