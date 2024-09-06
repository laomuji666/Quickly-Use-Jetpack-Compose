package com.laomuji666.compose.core.logic.repository.contacts

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource

interface ContactInfo{
    val nickname: String

    val category: String

    val avatarPainter: Painter
        @Composable get

    class InnerContactInfo(
        override val nickname: String,
        override val category: String,
        private val resId:Int
    ): ContactInfo{
        override val avatarPainter: Painter
            @Composable get() = painterResource(id = resId)
    }
}

fun List<ContactInfo>.getTypeList():List<String>{
    val result:MutableSet<String> = mutableSetOf()
    forEach {
        result.add(it.category)
    }
    return result.toList()
}