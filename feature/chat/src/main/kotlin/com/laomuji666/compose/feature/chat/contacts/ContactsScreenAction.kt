package com.laomuji666.compose.feature.chat.contacts

sealed interface ContactsScreenAction {
    data object UpdateContactList : ContactsScreenAction
}