package com.laomuji666.compose.feature.chat.contacts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laomuji666.compose.core.logic.database.entity.ContactInfoEntity
import com.laomuji666.compose.core.logic.repository.module.contacts.ContactRepository
import com.laomuji666.compose.core.ui.stateInTimeout
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class ContactsViewModel @Inject constructor(
    contactsRepository: ContactRepository
) : ViewModel(){
    private val _contactList: MutableStateFlow<List<ContactInfoEntity>> = MutableStateFlow(emptyList())

    val uiState = _contactList
        .asStateFlow()
        .onStart {
            contactsRepository.contactsList().collect{
                _contactList.value = it
            }
        }
        .map {
            ContactsScreenUiState(
                contactList = it
            )
        }.stateInTimeout(viewModelScope, ContactsScreenUiState())
}