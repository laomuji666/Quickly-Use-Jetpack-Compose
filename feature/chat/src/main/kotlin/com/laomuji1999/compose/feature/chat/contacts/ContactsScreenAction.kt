package com.laomuji1999.compose.feature.chat.contacts

sealed interface ContactsScreenAction {
    data object UpdateContactList : ContactsScreenAction
}