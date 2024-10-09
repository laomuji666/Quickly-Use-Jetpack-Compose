package com.laomuji666.compose.core.ui.we.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.laomuji666.compose.core.ui.we.DefaultWeTheme
import com.laomuji666.compose.core.ui.we.WeTheme

@Composable
fun WeTableInput(
    title:String = "",
    value:String = "",
    tip:String = "",
    onValueChange:(String) -> Unit = {},
    onImeNext:() -> Unit = {}
){
    val focusRequester = remember { FocusRequester() }
    WeTableRow(
        center = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = title,
                    style = WeTheme.typography.title,
                    color = WeTheme.colorScheme.fontColor90,
                    modifier = Modifier.width(WeTheme.dimens.tableLabelWidth)
                )
                Spacer(modifier = Modifier.width(WeTheme.dimens.listPaddingHorizontal / 2))
                BasicTextField(
                    value = value,
                    onValueChange = onValueChange,
                    modifier = Modifier
                        .focusRequester(focusRequester)
                        .fillMaxHeight()
                        .weight(1f),
                    decorationBox = { innerTextField ->
                        Box(modifier = Modifier.fillMaxSize()){
                            Row(
                                modifier = Modifier.fillMaxSize(),
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                innerTextField()
                            }
                            if (value.isEmpty()) {
                                Row(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalAlignment = Alignment.CenterVertically
                                ){
                                    Text(
                                        text = tip,
                                        style = WeTheme.typography.title,
                                        color = WeTheme.colorScheme.fontColor50
                                    )
                                }
                            }
                        }
                    },
                    textStyle = WeTheme.typography.title.copy(color = WeTheme.colorScheme.fontColor90),
                    cursorBrush = SolidColor(WeTheme.colorScheme.cursorColor),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            onImeNext()
                        }
                    )
                )
            }
        },
        onClick = {
            focusRequester.requestFocus()
        },
        weTableRowOutlineType = WeTableRowOutlineType.PADDING_HORIZONTAL,
        backgroundColor = Color.Transparent
    )
}


@PreviewLightDark
@Composable
fun PreviewWeTableInput(){
    DefaultWeTheme {
        val focusManager = LocalFocusManager.current

        var name by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        WeScaffold {
            WeTableInput(
                title = "用户名",
                value = name,
                tip = "请输入用户名",
                onValueChange = {
                    name = it
                },
                onImeNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            )
            WeTableInput(
                title = "邮箱号",
                value = email,
                tip = "请输入邮箱",
                onValueChange = {
                    email = it
                },
                onImeNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            )
            WeTableInput(
                title = "密码",
                value = password,
                tip = "请输入密码",
                onValueChange = {
                    password = it
                },
                onImeNext = {
                    focusManager.clearFocus()
                }
            )
        }
    }
}