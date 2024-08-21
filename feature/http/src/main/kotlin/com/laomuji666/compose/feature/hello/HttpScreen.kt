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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.laomuji666.compose.core.ui.QuicklyTheme

@Composable
fun HttpScreen(
    viewModel: HttpViewModel = hiltViewModel()
){
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
                text = "发送GET请求",
                onClick = onClickSendGet
            )
            HttpScreenSlot(
                text = "发送POST请求",
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

