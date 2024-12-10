package com.laomuji666.compose.core.logic.common

import android.content.Context
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object Toast{
    fun showText(context: Context, text: CharSequence){
        CoroutineScope(Dispatchers.Main).launch {
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
        }
    }
    fun showText(context: Context, resId: Int){
        val text = context.resources.getText(resId)
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}
