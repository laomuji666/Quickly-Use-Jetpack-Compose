package com.laomuji666.compose.feature.painter

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.laomuji666.compose.core.ui.theme.QuicklyTheme
import com.laomuji666.compose.core.ui.we.widget.WeButton
import com.laomuji666.compose.core.ui.we.widget.WeButtonColor
import com.laomuji666.compose.core.ui.we.widget.WeButtonType
import com.laomuji666.compose.core.ui.we.widget.WeTableRowOutline
import kotlin.math.abs

@Composable
fun PainterScreen(
    viewModel: PainterScreenViewModel = hiltViewModel()
){
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    PainterScreenUi(
        colorList = uiState.colorList,
        currentColorIndex = uiState.currentColorIndex,
        widthList = uiState.widthList,
        currentWidthIndex = uiState.currentWidthIndex,
        pathList = uiState.pathList,
        currentPath = uiState.currentPath,
        onCurrentColorIndexChange = {
            viewModel.onAction(PainterScreenViewModel.PainterScreenAction.OnCurrentColorIndexChange(it))
        },
        onCurrentWidthIndexChange = {
            viewModel.onAction(PainterScreenViewModel.PainterScreenAction.OnCurrentWidthIndexChange(it))
        },
        onClearCanvasClick = {
            viewModel.onAction(PainterScreenViewModel.PainterScreenAction.OnClearCanvas)
        },
        onDragStart = {
            viewModel.onAction(PainterScreenViewModel.PainterScreenAction.OnDragStart(it))
        },
        onDragEnd = {
            viewModel.onAction(PainterScreenViewModel.PainterScreenAction.OnDragEnd)
        },
        onDrag = {
            viewModel.onAction(PainterScreenViewModel.PainterScreenAction.OnDrag(it))
        }
    )
}

@Composable
private fun PainterScreenUi(
    colorList:List<Color>,
    currentColorIndex:Int,
    widthList:List<Dp>,
    currentWidthIndex:Int,
    pathList:List<PathData>,
    currentPath:PathData?,
    onCurrentColorIndexChange:(Int) -> Unit,
    onCurrentWidthIndexChange:(Int) -> Unit,
    onClearCanvasClick:()->Unit,
    onDragStart:(Offset)->Unit,
    onDragEnd:()->Unit,
    onDrag:(Offset) -> Unit
){
    val density = LocalDensity.current
    Column(modifier = Modifier.fillMaxSize().background(Color.White)) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .pointerInput(Unit){
                    detectDragGestures(
                        onDragStart = onDragStart,
                        onDragEnd = onDragEnd,
                        onDragCancel = onDragEnd,
                        onDrag = {change, _ ->
                            change.consume()
                            onDrag(change.position)
                        }
                    )
                }
                .drawWithCache {
                    onDrawWithContent {
                        drawContent()
                    }
                }
        ){
            pathList.forEach {
                drawPathData(
                    pathData = it,
                    strokeWidth = with(density){
                        it.width.toPx()
                    }
                )
            }
            currentPath?.let {
                drawPathData(
                    pathData = it,
                    strokeWidth = with(density){
                        it.width.toPx()
                    }
                )
            }
        }
        WeTableRowOutline(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            contentPadding = PaddingValues(horizontal = 12.dp)
        ) {
            itemsIndexed(widthList){ index, width ->
                val isSelected = index == currentWidthIndex
                Box(
                    modifier = Modifier
                        .graphicsLayer {
                            scaleX = if(isSelected) 1.25f else 1f
                            scaleY = if(isSelected) 1.25f else 1f
                        }
                        .width(60.dp)
                        .height(30.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .border(1.dp, if(isSelected) Color.Black else Color.Transparent, RoundedCornerShape(4.dp))
                        .clickable { onCurrentWidthIndexChange(index) }
                ){
                    Text(
                        text = "$width",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        WeTableRowOutline(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 12.dp)
        ) {
            itemsIndexed(colorList){ index, color ->
                val isSelected = index == currentColorIndex
                Spacer(
                    modifier = Modifier
                        .graphicsLayer {
                            scaleX = if(isSelected) 1.25f else 1f
                            scaleY = if(isSelected) 1.25f else 1f
                        }
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(color)
                        .border(2.dp, if(isSelected) Color.Black else Color.Transparent, CircleShape)
                        .clickable { onCurrentColorIndexChange(index) }
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            WeButton(
                weButtonType = WeButtonType.BIG,
                weButtonColor = WeButtonColor.PRIMARY,
                text = "清空画板",
                onClick = onClearCanvasClick
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
    }
}

private fun DrawScope.drawPathData(
    pathData: PathData,
    strokeWidth: Float,
    cap: StrokeCap = StrokeCap.Round
){
    val smoothness = 5
    val smoothedPath = Path().apply {
        if(pathData.path.isEmpty())return@apply
        moveTo(pathData.path.first().x, pathData.path.first().y)
        for(i in 1 until pathData.path.size){
            val from = pathData.path[i - 1]
            val to = pathData.path[i]
            val dx = abs(from.x - to.x)
            val dy = abs(from.y - to.y)
            if(dx > smoothness || dy > smoothness){
                quadraticTo(
                    x1 = (from.x + to.x) / 2,
                    y1 = (from.y + to.y) / 2,
                    x2 = to.x,
                    y2 = to.y
                )
            }
        }
    }
    drawPath(
        path = smoothedPath,
        color = pathData.color,
        style = Stroke(
            width = strokeWidth,
            cap = cap
        )
    )
}

@Preview
@Composable
private fun PreviewPainterScreen() {
    var currentColorIndex by remember {
        mutableIntStateOf(0)
    }
    var currentWidthIndex by remember {
        mutableIntStateOf(0)
    }
    QuicklyTheme {
        PainterScreenUi(
            colorList = listOf(
                Color(0xFFFF0000),
                Color(0xFFFFA500),
                Color(0xFFFFFF00),
                Color(0xFF00FF00),
                Color(0xFF00FFFF),
                Color(0xFF0000FF),
                Color(0xFF800080)
            ),
            currentColorIndex = currentColorIndex,
            widthList = listOf(1.dp, 2.dp, 3.dp, 4.dp, 5.dp),
            currentWidthIndex = currentWidthIndex,
            pathList = emptyList(),
            currentPath = null,
            onCurrentColorIndexChange = {
                currentColorIndex = it
            },
            onCurrentWidthIndexChange = {
                currentWidthIndex = it
            },
            onClearCanvasClick = {},
            onDragStart = {},
            onDragEnd = {},
            onDrag = {}
        )
    }
}