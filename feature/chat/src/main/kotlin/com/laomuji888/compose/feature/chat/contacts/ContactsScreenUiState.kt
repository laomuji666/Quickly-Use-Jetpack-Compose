package com.laomuji888.compose.feature.chat.contacts

import com.laomuji888.compose.core.logic.database.entity.ContactInfoEntity
import com.laomuji888.compose.core.logic.database.entity.getTypeList

data class ContactsScreenUiState(
    val contactList:List<ContactInfoEntity> = listOf(),
    val typeList:List<String> = contactList.getTypeList(),
)