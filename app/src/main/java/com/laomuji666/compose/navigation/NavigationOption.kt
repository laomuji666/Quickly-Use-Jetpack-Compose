package com.laomuji666.compose.navigation

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
fun navOptionsPushBack(navHostController: NavHostController): NavOptions {
    return navOptions{
        //如果 当前有页面,那么就保存数据
        if(navHostController.currentDestination!=null){
            //导航页面返回时,返回当前页面
            popUpTo(navHostController.currentDestination!!.route!!){
                //保存当前页面的状态
                this.saveState = true
            }
        }
        //不允许重复打开同一个页面
        launchSingleTop = true
        //开启恢复状态
        restoreState = true
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
    lastPopBackJob = coroutineScope.launch {
        popBackStack()
        delay(1000)
        lastPopBackJob = null
    }
}