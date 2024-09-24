package com.laomuji666.compose.core.logic.repository.module.contacts

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.laomuji666.compose.core.logic.database.dao.ContactDao
import com.laomuji666.compose.core.logic.repository.module.contacts.ContactInfoConvert.asContactInfoEntityList
import com.laomuji666.compose.core.logic.repository.module.contacts.ContactInfoConvert.asContactInfoList
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class ContactRepository(
    private val contactDao: ContactDao
) {
    fun contactsList() = flow{
        //先使用缓存的列表
        emit(contactDao.getAll().asContactInfoList())

        //使用firebase列表
        val result = ArrayList<ContactInfo>()
        val users = Firebase.database.getReference("users").get().await().value
        @Suppress("UNCHECKED_CAST")
        val userList = users as List<HashMap<String,*>>
        userList.forEach { user ->
            result.add(getContactWithFirebase(user))
        }

        //缓存到数据库
        contactDao.insertAll(result.asContactInfoEntityList())
        //使用保存的列表
        emit(contactDao.getAll().asContactInfoList())
    }.catch {
        it.printStackTrace()
        emit(ArrayList())
    }
}