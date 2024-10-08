package com.laomuji666.compose.core.ui.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.laomuji666.compose.core.ui.theme.QuicklyTheme
import com.laomuji666.compose.res.R

class ErrorViewContent{
    @Composable
    fun uniqueKey():String{
        val saveData = rememberSaveable {
            "${System.currentTimeMillis()}"
        }
        return saveData
    }
}

@Composable
fun ErrorView(copyContent: @Composable ErrorViewContent.() -> Unit) {
    var isClickRefresh by rememberSaveable { mutableStateOf(false) }
    if (isClickRefresh) {
        copyContent(ErrorViewContent())
    } else {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                imageVector = Icons.Default.Close,
                contentDescription = null,
                modifier = Modifier.width(180.dp),
                contentScale = ContentScale.FillWidth,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.error)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(id = R.string.string_error_view_title),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = stringResource(id = R.string.string_error_view_content),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(15.dp))
            Surface(
                modifier = Modifier
                    .height(50.dp)
                    .width(180.dp),
                onClick = {isClickRefresh = true},
                shape = CircleShape,
                color = Color.Transparent,
                border = BorderStroke(2.dp, MaterialTheme.colorScheme.error)
            ) {
                Box(modifier = Modifier.fillMaxSize()){
                    Text(
                        text = stringResource(id = R.string.string_error_view_refresh),
                        modifier = Modifier.align(Alignment.Center),
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }

        }
    }
}

@Preview
@Composable
fun PreviewErrorView() {
    QuicklyTheme {
        ErrorView{

        }
    }
}