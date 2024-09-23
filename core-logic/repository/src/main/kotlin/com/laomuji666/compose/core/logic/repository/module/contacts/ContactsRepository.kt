package com.laomuji666.compose.core.logic.repository.module.contacts

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class ContactsRepository {
    fun contactsList() = flow{
        val result = ArrayList<ContactInfo>()
        val users = Firebase.database.getReference("users").get().await().value
        @Suppress("UNCHECKED_CAST")
        val userList = users as List<HashMap<String,*>>
        userList.forEach { user ->
            result.add(ContactInfo.FirebaseContactInfo(user))
        }
        emit(result)
    }.catch {
        it.printStackTrace()
        emit(ArrayList())
    }
}