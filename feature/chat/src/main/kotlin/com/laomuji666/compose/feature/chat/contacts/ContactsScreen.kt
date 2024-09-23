package com.laomuji666.compose.feature.chat.contacts

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.laomuji666.compose.core.logic.repository.module.contacts.ContactInfo
import com.laomuji666.compose.core.logic.repository.module.contacts.getTypeList
import com.laomuji666.compose.core.ui.theme.QuicklyTheme
import com.laomuji666.compose.core.ui.we.WeTheme
import com.laomuji666.compose.core.ui.we.widget.WeContactItem
import com.laomuji666.compose.core.ui.we.widget.WePullToRefreshContainer
import com.laomuji666.compose.core.ui.we.widget.WeScaffold
import com.laomuji666.compose.core.ui.we.widget.WeTableTitle
import com.laomuji666.compose.feature.chat.AiChatTopBar
import com.laomuji666.compose.res.R
import kotlinx.coroutines.launch

@Composable
fun ContactsScreen(
    viewModel: ContactsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    WeScaffold(
        topBar = {
            AiChatTopBar(
                title = stringResource(id = R.string.string_ai_chat_screen_navigation_message),
                onMenuClick = {}
            )
        }
    ) {
        ContactsScreenUi(
            contactList = uiState.contactList
        )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ContactsScreenUi(
    paddingTop: Dp = WeTheme.dimens.topNavigationBarHeight - 1.dp,
    contactList:List<ContactInfo>
){
    val paddingTopPx = with(LocalDensity.current){
        paddingTop.toPx()
    }
    val pullToRefreshState = rememberPullToRefreshState()
    val listState = rememberScrollState()
    val typeList = contactList.getTypeList()
    val typeMap = mutableMapOf<String,Float>()
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()){
        Column(
            modifier = Modifier
                .background(WeTheme.colorScheme.background)
                .nestedScroll(pullToRefreshState.nestedScrollConnection)
                .fillMaxSize(),
        ) {
            WePullToRefreshContainer(
                state = pullToRefreshState
            )
            Column(
                modifier = Modifier.verticalScroll(state = listState)
            ){
                contactList.forEachIndexed { index,item ->
                    if(index == 0){
                        WeTableTitle(
                            modifier = Modifier.onGloballyPositioned {
                                typeMap[item.category] = it.positionInRoot().y - it.size.height
                            },
                            title = item.category
                        )
                    }else{
                        if(item.category != contactList[index - 1].category){
                            WeTableTitle(
                                modifier = Modifier.onGloballyPositioned {
                                    typeMap[item.category] = it.positionInRoot().y - it.size.height
                                },
                                title = item.category
                            )
                        }
                    }
                    WeContactItem(
                        painter = item.avatarPainter,
                        text = item.nickname
                    )
                }
            }
        }
        Column(
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            typeList.forEach { item ->
                Text(
                    modifier = Modifier
                        .clickable {
                            coroutineScope.launch {
                                listState.scrollBy((typeMap[item] ?: 0f) - paddingTopPx)
                            }
                        }
                        .padding(WeTheme.dimens.listPaddingHorizontal / 2),
                    text = item,
                    style = WeTheme.typography.small,
                    color = WeTheme.colorScheme.fontColor50
                )
            }
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@PreviewLightDark
@Composable
fun PreviewContactsScreenUi(){
    QuicklyTheme {
        ContactsScreenUi(
            contactList = listOf(
                ContactInfo.InnerContactInfo(
                    account = 1001,
                    nickname = "A",
                    category = "字母",
                    resId = R.mipmap.ic_launcher
                ),
                ContactInfo.InnerContactInfo(
                    account = 1002,
                    nickname = "B",
                    category = "字母",
                    resId = R.mipmap.ic_launcher
                ),
                ContactInfo.InnerContactInfo(
                    account = 1003,
                    nickname = "C",
                    category = "字母",
                    resId = R.mipmap.ic_launcher
                ),
                ContactInfo.InnerContactInfo(
                    account = 1004,
                    nickname = "111",
                    category = "数字",
                    resId = R.mipmap.ic_launcher
                ),
                ContactInfo.InnerContactInfo(
                    account = 1005,
                    nickname = "222",
                    category = "数字",
                    resId = R.mipmap.ic_launcher
                ),
            )
        )
    }
}