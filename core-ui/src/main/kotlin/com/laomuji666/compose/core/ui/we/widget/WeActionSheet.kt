package com.laomuji666.compose.core.ui.we.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.laomuji666.compose.core.ui.theme.QuicklyTheme
import com.laomuji666.compose.core.ui.we.LocalWeDimens
import com.laomuji666.compose.core.ui.we.WeTheme
import com.laomuji666.compose.core.ui.we.animated.AnimatedSlide

enum class WeActionSheetType{
    SUMMARY,
    NORMAL,
    WRONG
}

@Composable
fun WeActionSheetRow(
    text:String,
    onClick:()->Unit = {},
    weActionSheetType: WeActionSheetType = WeActionSheetType.NORMAL,
    weTableRowOutlineType: WeTableRowOutlineType = WeTableRowOutlineType.NONE
){
    WeTableRow(
        start = {
            Spacer(modifier = Modifier.weight(1f))
        },
        center = {
            when(weActionSheetType){
                WeActionSheetType.SUMMARY -> {
                    Text(
                        text = text,
                        style = WeTheme.weTypography.groupTitle,
                        color = WeTheme.weColorScheme.onRowBackSecondaryColor
                    )
                }
                WeActionSheetType.NORMAL -> {
                    Text(
                        text = text,
                        style = WeTheme.weTypography.largeText,
                        color = WeTheme.weColorScheme.onBackgroundColor
                    )
                }
                WeActionSheetType.WRONG -> {
                    Text(
                        text = text,
                        style = WeTheme.weTypography.largeText,
                        color = WeTheme.weColorScheme.error
                    )
                }
            }
        },
        end = {
            Spacer(modifier = Modifier.weight(1f))
        },
        onClick = onClick,
        weTableRowOutlineType = weTableRowOutlineType
    )
}

@Composable
fun WeActionSheetDialog(
    onDismissRequest: ()->Unit = {},
    dismissText:String?=null,
    content: @Composable ColumnScope.()->Unit
){
    Dialog(
        onDismissRequest = {},
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        AnimatedSlide(scope = {
            Show()
        }){
            val dismissRequest = {
                hide { onDismissRequest() }
            }
            Box(
                modifier = Modifier
                    .clickable { dismissRequest() }
                    .fillMaxSize()
            ){
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .clip(
                            RoundedCornerShape(
                                topStart = LocalWeDimens.current.roundedCornerDp,
                                topEnd = LocalWeDimens.current.roundedCornerDp
                            )
                        )
                ) {
                    content()
                    dismissText?.let{
                        WeActionSheetRow(
                            text = it,
                            onClick = dismissRequest
                        )
                    }
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
fun PreviewWeActionSheetRow1(){
    var showDialog by remember {
        mutableStateOf(true)
    }
    QuicklyTheme {
        if(showDialog){
            WeActionSheetDialog(
                onDismissRequest = { showDialog = false },
                dismissText = "取消"
            ){
                WeActionSheetRow(
                    text = "警示操作提示文案",
                    weTableRowOutlineType = WeTableRowOutlineType.FULL,
                    weActionSheetType = WeActionSheetType.SUMMARY
                )
                WeActionSheetRow(
                    "操作一",
                    weTableRowOutlineType = WeTableRowOutlineType.FULL
                )
                WeActionSheetRow(
                    "操作二",
                    weTableRowOutlineType = WeTableRowOutlineType.FULL
                )
                WeActionSheetRow(
                    "操作三",
                    weTableRowOutlineType = WeTableRowOutlineType.FULL
                )
                WeActionSheetRow(
                    text = "警示操作",
                    weActionSheetType = WeActionSheetType.WRONG,
                    weTableRowOutlineType = WeTableRowOutlineType.SPLIT
                )
            }
        }
    }
}