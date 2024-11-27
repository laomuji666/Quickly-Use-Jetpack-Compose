package com.laomuji666.compose.core.logic.repository.module.contacts

import com.laomuji666.compose.core.logic.database.dao.ContactDao
import com.laomuji666.compose.core.logic.database.entity.ContactInfoEntity
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class ContactRepository(
    private val contactDao: ContactDao
) {
    fun contactsList() = flow{
        //先使用缓存的列表
        emit(contactDao.getAll())

        //获取后台的联系人列表, 因为没有后台,这里弄成从本地添加
        val contacts = fakeRequestContacts()

        //缓存到数据库
        contactDao.insertAll(contacts)
        //使用保存的列表
        emit(contactDao.getAll())
    }.catch {
        it.printStackTrace()
        emit(ArrayList())
    }

    private fun fakeRequestContacts(): List<ContactInfoEntity>{
        return listOf(
            ContactInfoEntity(
                account = 10000,
                avatar = "content://compose.laomuji666.contact.assert/avatar/avatar_ragdoll_cat.png",
                category = "宠物",
                nickname = "布偶猫"
            ),
            ContactInfoEntity(
                account = 10001,
                avatar = "content://compose.laomuji666.contact.assert/avatar/avatar_golden_dog.png",
                category = "宠物",
                nickname = "金毛狗"
            ),
            ContactInfoEntity(
                account = 10002,
                avatar = "content://compose.laomuji666.contact.assert/avatar/avatar_parrot.png",
                category = "宠物",
                nickname = "鹦鹉"
            ),
            ContactInfoEntity(
                account = 10003,
                avatar = "content://compose.laomuji666.contact.assert/avatar/avatar_siberian_husky.png",
                category = "宠物",
                nickname = "哈士奇"
            ),
            ContactInfoEntity(
                account = 10004,
                avatar = "content://compose.laomuji666.contact.assert/avatar/avatar_british_shorthair.png",
                category = "宠物",
                nickname = "蓝猫"
            ),
            ContactInfoEntity(
                account = 10005,
                avatar = "content://compose.laomuji666.contact.assert/avatar/avatar_sheep.png",
                category = "动物",
                nickname = "绵羊"
            ),
            ContactInfoEntity(
                account = 10006,
                avatar = "content://compose.laomuji666.contact.assert/avatar/avatar_tiger.png",
                category = "动物",
                nickname = "老虎"
            ),
            ContactInfoEntity(
                account = 10007,
                avatar = "content://compose.laomuji666.contact.assert/avatar/avatar_mouse.png",
                category = "动物",
                nickname = "老鼠"
            ),
            ContactInfoEntity(
                account = 10008,
                avatar = "content://compose.laomuji666.contact.assert/avatar/avatar_giraffe.png",
                category = "动物",
                nickname = "长颈鹿"
            ),
            ContactInfoEntity(
                account = 1009,
                avatar = "content://compose.laomuji666.contact.assert/avatar/avatar_eagle.png",
                category = "动物",
                nickname = "老鹰"
            ),
            ContactInfoEntity(
                account = 10010,
                avatar = "content://compose.laomuji666.contact.assert/avatar/avatar_wolf.png",
                category = "动物",
                nickname = "狼"
            ),
            ContactInfoEntity(
                account = 10011,
                avatar = "content://compose.laomuji666.contact.assert/avatar/avatar_leopard.png",
                category = "动物",
                nickname = "金钱豹"
            ),
            ContactInfoEntity(
                account = 10012,
                avatar = "content://compose.laomuji666.contact.assert/avatar/avatar_bee.png",
                category = "昆虫",
                nickname = "蜜蜂"
            ),
            ContactInfoEntity(
                account = 10013,
                avatar = "content://compose.laomuji666.contact.assert/avatar/avatar_butterfly.png",
                category = "昆虫",
                nickname = "蝴蝶"
            ),
            ContactInfoEntity(
                account = 10014,
                avatar = "content://compose.laomuji666.contact.assert/avatar/avatar_mantis.png",
                category = "昆虫",
                nickname = "螳螂"
            )
        )
    }
}