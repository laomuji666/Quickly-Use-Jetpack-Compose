package com.laomuji666.compose.core.logic.util

import android.content.Context
import android.widget.Toast

object Toast{
    fun showText(context: Context, text: CharSequence){
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
    fun showText(context: Context, resId: Int){
        val text = context.resources.getText(resId)
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}
