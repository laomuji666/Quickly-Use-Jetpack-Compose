package com.laomuji666.compose.feature.chat.contacts

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.laomuji666.compose.core.logic.repository.contacts.ContactInfo
import com.laomuji666.compose.core.logic.repository.contacts.ContactsRepository
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
){
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    ContactsScreenUi(
        contactList = uiState.contactList
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ContactsScreenUi(
    contactList:List<ContactInfo>
){
    val pullToRefreshState = rememberPullToRefreshState()

    Column(
        modifier = Modifier
            .background(WeTheme.colorScheme.background)
            .nestedScroll(pullToRefreshState.nestedScrollConnection)
            .fillMaxSize(),
    ) {
        WePullToRefreshContainer(
            state = pullToRefreshState
        )
        LazyColumn{
            itemsIndexed(contactList){ index,item ->
                if(index == 0){
                    WeTableTitle(
                        title = item.category
                    )
                }else{
                    if(item.category != contactList[index - 1].category){
                        WeTableTitle(
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