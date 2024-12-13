package com.laomuji666.compose.feature.scroll

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.laomuji666.compose.core.ui.theme.QuicklyTheme
import com.laomuji666.compose.core.ui.we.widget.WeScaffold
import com.laomuji666.compose.res.R

/**
 * 子节点对父节点的滑动进行劫持
 */
@Composable
fun NestedScrollDispatcherScreen(){
    val density = LocalDensity.current

    val imageHeightMax by remember {
        mutableFloatStateOf(with(density){
            300.dp.toPx()
        })
    }
    val imageHeightMin by remember {
        mutableFloatStateOf(with(density){
            100.dp.toPx()
        })
    }
    var imageHeight by remember {
        mutableFloatStateOf(imageHeightMax)
    }

    val nestedScrollDispatcher = remember {
        NestedScrollDispatcher()
    }
    WeScaffold {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(50){
                Text("$it")
            }
            item {
                Image(
                    painter = painterResource(id = R.mipmap.ic_launcher),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .height(with(density){
                            imageHeight.toDp()
                        })
                        .fillMaxWidth()
                        .draggable(
                            orientation = Orientation.Vertical,
                            state = rememberDraggableState { onDelta ->
                                //处理图片本身的滑动,在收缩或者展开后不再使用滑动
                                val consumedY = if(onDelta < 0){
                                    val remainHeight = imageHeightMin - imageHeight
                                    if(remainHeight < onDelta){
                                        onDelta
                                    }else {
                                        remainHeight
                                    }
                                }else{
                                    val remainHeight = imageHeightMax - imageHeight
                                    if(remainHeight > onDelta) {
                                        onDelta
                                    }else{
                                        remainHeight
                                    }
                                }
                                imageHeight+=consumedY


                                //把多余的滑动交给父节点,去掉后不会把滑动传给父节点
                                nestedScrollDispatcher.dispatchPostScroll(
                                    consumed = Offset(0f, consumedY),
                                    available = Offset(0f, onDelta - consumedY),
                                    source = NestedScrollSource.UserInput
                                )
                            }
                        )
                        .nestedScroll(
                            connection = remember { object : NestedScrollConnection {} },
                            dispatcher = nestedScrollDispatcher
                        )
                )
            }
            items(50){
                Text("$it")
            }
        }
    }
}

@Preview
@Composable
private fun PreviewNestedScrollDispatcherScreen() {
    QuicklyTheme {
        WeScaffold {
            NestedScrollDispatcherScreen()
        }
    }
}