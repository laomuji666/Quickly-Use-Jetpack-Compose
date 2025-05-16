package com.laomuji888.compose.core.ui

import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.navOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * 增加一个屏幕
 * 新的屏幕会在添加在当前屏幕后
 */
fun NavHostController.navOptionsPushBack(): NavOptions {
    return navOptions{
        if(currentDestination!=null){
            popUpTo(currentDestination!!.route!!){
                this.saveState = true
            }
        }
    }
}

/**
 * 增加一个屏幕
 * 从新的屏幕返回时 退出
 */
fun navOptionsRemoveAll(): NavOptions {
    return navOptions{
        popUpTo(0)
    }
}

/**
 * 一秒钟内只允许弹出一次
 * 防止用户点击过快
 */
private var lastPopBackJob: Job? = null
private val coroutineScope = CoroutineScope(Dispatchers.Main)
fun NavHostController.safePopBackStack(){
    if(lastPopBackJob !=null && lastPopBackJob!!.isActive){
        return
    }
    lastPopBackJob?.cancel()
    popBackStack()
    lastPopBackJob = coroutineScope.launch {
        delay(1000)
        lastPopBackJob = null
    }
}
