package com.laomuji666.compose.feature.hello

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.laomuji666.compose.core.ui.theme.QuicklyTheme
import com.laomuji666.compose.core.ui.view.ErrorView
import com.laomuji666.compose.core.ui.we.WeTheme
import com.laomuji666.compose.core.ui.we.widget.WeButton
import com.laomuji666.compose.core.ui.we.widget.WeButtonSizeType
import com.laomuji666.compose.core.ui.we.widget.WeButtonType
import com.laomuji666.compose.core.ui.we.widget.WeToast
import com.laomuji666.compose.core.ui.we.widget.WeToastType
import com.laomuji666.compose.core.ui.we.widget.WeTopBar
import com.laomuji666.compose.res.R

@Composable
fun HttpScreen(
    viewModel: HttpViewModel = hiltViewModel(),
    onBackClick:()->Unit
){
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    if(uiState.isError){
        ErrorView {
            HttpScreen(
                viewModel = hiltViewModel(key = uniqueKey()),
                onBackClick = onBackClick
            )
        }
        return
    }

    if(uiState.isLoading){
        WeToast(weToastType = WeToastType.LOADING)
    }

    HttpScreenUi(
        responseText = uiState.responseText,
        onBackClick = onBackClick,
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
    responseText:String,
    onBackClick:()->Unit,
    onClickSendGet:()->Unit,
    onClickSendPost:()->Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(WeTheme.weColorScheme.backgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WeTopBar(
            title = stringResource(id = R.string.string_hello_screen_http_demo),
            onBackClick = onBackClick
        )
        Spacer(modifier = Modifier.height(20.dp))
        HttpScreenSlot(
            text = stringResource(id = R.string.string_http_screen_get_demo),
            onClick = onClickSendGet
        )
        HttpScreenSlot(
            text = stringResource(id = R.string.string_http_screen_post_demo),
            onClick = onClickSendPost
        )
        Text(text = responseText, color = WeTheme.weColorScheme.onBackgroundColor)
    }
}

@Composable
private fun HttpScreenSlot(
    text:String,
    onClick:()->Unit
){
    WeButton(weButtonType = WeButtonType.PRIMARY, weButtonSizeType = WeButtonSizeType.BIG, text = text) {
        onClick()
    }
    Spacer(modifier = Modifier.height(20.dp))
}

@Preview
@Composable
fun PreviewHttpScreen(){
    QuicklyTheme {
        HttpScreenUi(
            onBackClick = {},
            onClickSendGet = {},
            onClickSendPost = {},
            responseText = ""
        )
    }
}

