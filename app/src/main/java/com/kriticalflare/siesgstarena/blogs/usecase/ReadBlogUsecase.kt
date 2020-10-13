package com.kriticalflare.siesgstarena.blogs.usecase

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import com.kriticalflare.siesgstarena.R
import com.kriticalflare.siesgstarena.models.Blog
import com.kriticalflare.siesgstarena.utils.IntentUtils

class ReadBlogUsecase(val blog: Blog, val context: Context) {

    fun openCustomTab() {
        if (IntentUtils.isCustomTabSupported(context)) {
            val customTabsIntent = CustomTabsIntent.Builder()
                .setToolbarColor(ContextCompat.getColor(context, R.color.purple_200))
                .addDefaultShareMenuItem()
                .setShowTitle(true)
                .build()
            customTabsIntent.launchUrl(context, Uri.parse(blog.createBlogUrl()))
        } else {
            val blogIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse("http://www.example.com"))
            context.startActivity(blogIntent)
        }
    }
}
