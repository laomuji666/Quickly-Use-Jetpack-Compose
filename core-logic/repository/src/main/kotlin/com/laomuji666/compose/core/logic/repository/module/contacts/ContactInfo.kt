package com.laomuji666.compose.core.logic.repository.module.contacts

import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.laomuji666.compose.core.logic.database.entity.ContactInfoEntity
import kotlinx.coroutines.tasks.await

data class ContactInfo(
    val account:Long,
    val nickname: String,
    val category: String,
    val avatar: String
)

object ContactInfoConvert{
    private fun asContactInfoEntity(contactInfo: ContactInfo): ContactInfoEntity {
        return ContactInfoEntity(contactInfo.account,contactInfo.nickname,contactInfo.category,contactInfo.avatar)
    }

    private fun ContactInfoEntity.asContactInfo(): ContactInfo {
        return ContactInfo(account,nickname,category,avatar)
    }

    fun List<ContactInfoEntity>.asContactInfoList():List<ContactInfo>{
        val result:MutableList<ContactInfo> = mutableListOf()
        forEach {
            result.add(it.asContactInfo())
        }
        return result.sortedByDescending { it.category }
    }

    fun List<ContactInfo>.asContactInfoEntityList():List<ContactInfoEntity>{
        val result:MutableList<ContactInfoEntity> = mutableListOf()
        forEach {
            result.add(asContactInfoEntity(it))
        }
        return result
    }
}

suspend fun getContactWithFirebase(map:Map<String,*>):ContactInfo{
    val account = map["account"] as Long
    val nickname = map["nickname"] as String
    val category = map["category"] as String
    val avatar = map["avatar"] as String
    //这里有个问题,获取的头像链接是异步的,实际上应该直接返回,导致获取信息的速度变慢.
    val downloadUrl = Firebase.storage.reference.child(avatar).downloadUrl.await().toString()
    return ContactInfo(account,nickname,category,downloadUrl)
}

fun List<ContactInfo>.getTypeList():List<String>{
    val result:MutableSet<String> = mutableSetOf()
    forEach {
        result.add(it.category)
    }
    return result.toList()
}