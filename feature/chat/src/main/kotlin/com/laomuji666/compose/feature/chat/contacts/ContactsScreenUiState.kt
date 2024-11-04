package com.laomuji666.compose.feature.chat.contacts

import com.laomuji666.compose.core.logic.database.entity.ContactInfoEntity
import com.laomuji666.compose.core.logic.database.entity.getTypeList

data class ContactsScreenUiState(
    val contactList:List<ContactInfoEntity> = listOf(),
    val typeList:List<String> = contactList.getTypeList(),
)