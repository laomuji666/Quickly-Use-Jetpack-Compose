package com.laomuji888.compose.feature.chat.contacts

sealed interface ContactsScreenAction {
    data object UpdateContactList : ContactsScreenAction
}