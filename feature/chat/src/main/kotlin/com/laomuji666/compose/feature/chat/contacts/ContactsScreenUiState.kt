package com.laomuji666.compose.feature.chat.contacts

import com.laomuji666.compose.core.logic.repository.contacts.ContactInfo

data class ContactsScreenUiState(
    val typeList:List<String> = listOf(),
    val contactList:List<ContactInfo> = listOf()
)