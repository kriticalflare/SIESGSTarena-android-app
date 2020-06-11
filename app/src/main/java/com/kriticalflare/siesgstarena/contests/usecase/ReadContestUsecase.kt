package com.kriticalflare.siesgstarena.contests.usecase

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import com.kriticalflare.siesgstarena.R
import com.kriticalflare.siesgstarena.models.Contest
import com.kriticalflare.siesgstarena.utils.IntentUtils

class ReadContestUsecase(val contest: Contest, val context: Context) {

    fun openCustomTab() {
        if (IntentUtils.isCustomTabSupported(context)) {
            val customTabsIntent = CustomTabsIntent.Builder()
                .setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .addDefaultShareMenuItem()
                .setShowTitle(true)
                .build()
            customTabsIntent.launchUrl(context, Uri.parse(contest.createContestUrl()))
        } else {
            val blogIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse("http://www.example.com"))
            context.startActivity(blogIntent)
        }
    }
}
