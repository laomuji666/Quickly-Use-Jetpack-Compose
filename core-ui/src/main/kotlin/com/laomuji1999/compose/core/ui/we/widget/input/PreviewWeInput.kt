package com.laomuji1999.compose.core.ui.we.widget.input

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.laomuji1999.compose.core.ui.theme.QuicklyTheme
import com.laomuji1999.compose.core.ui.we.widget.scaffold.WeScaffold


@PreviewLightDark
@Composable
fun PreviewWeInput() {
    QuicklyTheme {
        val focusManager = LocalFocusManager.current

        var name by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        WeScaffold {
            WeInput(title = "用户名", value = name, tip = "请输入用户名", onValueChange = {
                name = it
            }, keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next), onImeAction = {
                focusManager.moveFocus(FocusDirection.Down)
            })
            WeInput(title = "邮箱号", value = email, tip = "请输入邮箱", onValueChange = {
                email = it
            }, keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next), onImeAction = {
                focusManager.moveFocus(FocusDirection.Down)
            })
            WeInput(title = "密码", value = password, tip = "请输入密码", onValueChange = {
                password = it
            }, keyboardOptions = KeyboardOptions(imeAction = ImeAction.Go), onImeAction = {
                focusManager.clearFocus()
            })
        }
    }
}