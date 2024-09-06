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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.laomuji666.compose.core.logic.repository.contacts.ContactInfo
import com.laomuji666.compose.core.logic.repository.contacts.ContactsRepository
import com.laomuji666.compose.core.logic.repository.contacts.getTypeList
import com.laomuji666.compose.core.ui.theme.QuicklyTheme
import com.laomuji666.compose.core.ui.we.WeTheme
import com.laomuji666.compose.core.ui.we.widget.WeContactItem
import com.laomuji666.compose.core.ui.we.widget.WePullToRefreshContainer
import com.laomuji666.compose.core.ui.we.widget.WeTableTitle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ContactsScreen(
    viewModel: ContactsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    ContactsScreenUi(
        contactList = uiState.contactList
    )
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
fun PreviewContactsScreen(){
    val context = LocalContext.current
    var contactList:List<ContactInfo> by remember {
        mutableStateOf(listOf())
    }
    if(contactList.isEmpty()){
        CoroutineScope(Dispatchers.Main).launch {
            ContactsRepository(context).contactsList().collect{
                contactList = it
            }
        }
    }

    QuicklyTheme {
        ContactsScreenUi(
            contactList = contactList
        )
    }
}