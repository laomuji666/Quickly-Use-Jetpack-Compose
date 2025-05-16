package com.laomuji888.compose.feature.chat.contacts

import com.laomuji888.compose.core.logic.database.entity.ContactInfoEntity

data class ContactsScreenUiState(
    val contactInfoList: List<ContactInfo> = emptyList()
){
    data class ContactInfo(
        val category: String,
        val contactList: List<ContactInfoEntity>
    )
}