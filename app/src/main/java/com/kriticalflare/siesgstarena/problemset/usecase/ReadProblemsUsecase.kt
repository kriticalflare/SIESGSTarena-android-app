package com.kriticalflare.siesgstarena.problemset.usecase

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import com.kriticalflare.siesgstarena.R
import com.kriticalflare.siesgstarena.models.Problem
import com.kriticalflare.siesgstarena.utils.IntentUtils

class ReadProblemsUsecase(val problem: Problem, val context: Context) {

    fun openCustomTab() {
        if (IntentUtils.isCustomTabSupported(context)) {
            val customTabsIntent = CustomTabsIntent.Builder()
                .setToolbarColor(ContextCompat.getColor(context, R.color.purple_200))
                .addDefaultShareMenuItem()
                .setShowTitle(true)
                .build()
            customTabsIntent.launchUrl(context, Uri.parse(problem.createProblemUrl()))
        } else {
            val problemIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse("http://www.example.com"))
            context.startActivity(problemIntent)
        }
    }
}
