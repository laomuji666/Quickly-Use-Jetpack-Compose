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
import com.laomuji666.compose.core.ui.we.animated.AnimatedSlide
import com.laomuji666.compose.core.ui.we.DefaultWeTheme
import com.laomuji666.compose.core.ui.we.WeTheme

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
                        style = WeTheme.typography.groupTitle,
                        color = WeTheme.colorScheme.fontColor50
                    )
                }
                WeActionSheetType.NORMAL -> {
                    Text(
                        text = text,
                        style = WeTheme.typography.title,
                        color = WeTheme.colorScheme.fontColor90
                    )
                }
                WeActionSheetType.WRONG -> {
                    Text(
                        text = text,
                        style = WeTheme.typography.title,
                        color = WeTheme.colorScheme.error
                    )
                }
            }
        },
        end = {
            Spacer(modifier = Modifier.weight(1f))
        },
        onClick = onClick,
        showClickIndication = false,
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
        Box(modifier = Modifier.fillMaxSize()){
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
                            .clip(RoundedCornerShape(
                                topStart = WeTheme.dimens.actionSheetRoundedCornerDp,
                                topEnd = WeTheme.dimens.actionSheetRoundedCornerDp
                            ))
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
}

@PreviewLightDark
@Composable
fun PreviewWeActionSheetRow1(){
    var showDialog by remember {
        mutableStateOf(true)
    }
    DefaultWeTheme {
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
                    weTableRowOutlineType = WeTableRowOutlineType.FULL,
                )
                WeActionSheetRow(
                    "操作二",
                    weTableRowOutlineType = WeTableRowOutlineType.FULL,
                )
                WeActionSheetRow(
                    "操作三",
                    weTableRowOutlineType = WeTableRowOutlineType.FULL,
                )
                WeActionSheetRow(
                    text = "警示操作",
                    weActionSheetType = WeActionSheetType.WRONG,
                    weTableRowOutlineType = WeTableRowOutlineType.SPLIT,
                )
            }
        }
    }
}