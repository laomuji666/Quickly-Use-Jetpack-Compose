package com.laomuji666.compose.feature.chat.contacts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laomuji666.compose.core.logic.database.dao.ContactDao
import com.laomuji666.compose.core.logic.repository.module.contacts.ContactRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ContactsViewModel @Inject constructor(
    contactsRepository: ContactRepository
) : ViewModel(){
    val uiState = contactsRepository.contactsList().map {
        ContactsScreenUiState(
            contactList = it
        )
    }.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000), ContactsScreenUiState()
    )
}