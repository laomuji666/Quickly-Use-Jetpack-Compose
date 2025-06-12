package com.laomuji1999.compose.core.ui.screen

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.layoutDirection
import com.laomuji1999.compose.core.ui.R
import java.util.Locale

/**
 * 不同Activity间的切换,使用滑动进入滑动退出的效果
 * @author laomuji666
 * @since 2025/5/23
 */
abstract class SlideActivity : AppCompatActivity() {
    override fun finish() {
        super.finish()
        animFinishActivitySlideOut()
    }

    override fun startActivity(intent: Intent?, options: Bundle?) {
        super.startActivity(intent, options)
        animStartActivitySlideInto()
    }

    @Deprecated("Deprecated in Java")
    override fun startActivityForResult(intent: Intent, requestCode: Int, options: Bundle?) {
        @Suppress("DEPRECATION")
        super.startActivityForResult(intent, requestCode, options)
        animStartActivitySlideInto()
    }

    @Deprecated("Deprecated in Java")
    override fun startActivityFromChild(
        child: Activity,
        intent: Intent?,
        requestCode: Int,
        options: Bundle?
    ) {
        @Suppress("DEPRECATION")
        super.startActivityFromChild(child, intent, requestCode, options)
        animStartActivitySlideInto()
    }

    override fun startActivityFromFragment(
        fragment: androidx.fragment.app.Fragment,
        intent: Intent?,
        requestCode: Int,
        options: Bundle?
    ) {
        super.startActivityFromFragment(fragment, intent, requestCode, options)
        animStartActivitySlideInto()
    }

    private fun animStartActivitySlideInto() {
        val slideIntoAnim = getSlideIntoAnim()
        @Suppress("DEPRECATION")
        overridePendingTransition(slideIntoAnim.first, slideIntoAnim.second)
    }

    private fun animFinishActivitySlideOut() {
        val slideIntoAnim = getSlideOutAnim()
        @Suppress("DEPRECATION")
        overridePendingTransition(slideIntoAnim.first, slideIntoAnim.second)
    }

    private fun getSlideIntoAnim(): Pair<Int, Int> {
        return if (isRTL()) {
            Pair(R.anim.anim_slide_left_in, R.anim.anim_slide_right_out)
        } else {
            Pair(R.anim.anim_slide_right_in, R.anim.anim_slide_left_out)
        }
    }

    private fun getSlideOutAnim(): Pair<Int, Int> {
        return if (isRTL()) {
            Pair(R.anim.anim_slide_right_in, R.anim.anim_slide_left_out)
        } else {
            Pair(R.anim.anim_slide_left_in, R.anim.anim_slide_right_out)
        }
    }

    private fun isRTL(): Boolean {
        val locale: Locale = if (resources.configuration.locales.isEmpty) {
            @Suppress("DEPRECATION")
            resources.configuration.locale
        } else {
            resources.configuration.locales.get(0)
        }
        return locale.layoutDirection == View.LAYOUT_DIRECTION_RTL
    }
}