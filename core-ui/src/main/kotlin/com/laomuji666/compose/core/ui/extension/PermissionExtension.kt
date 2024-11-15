package com.laomuji666.compose.core.ui.extension

import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalPermissionsApi::class)
fun PermissionStatus.isForeverDenied():Boolean{
    return !isGranted && shouldShowRationale
}