package com.kriticalflare.siesgstarena.utils

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.browser.customtabs.CustomTabsService.ACTION_CUSTOM_TABS_CONNECTION

object IntentUtils {

    fun isCustomTabSupported(context: Context): Boolean {
        val pm: PackageManager = context.packageManager
        // Get default VIEW intent handler.
        val activityIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.example.com"))
        // Get all apps that can handle VIEW intents.
        val resolvedActivityList =
            pm.queryIntentActivities(activityIntent, 0)
        for (info in resolvedActivityList) {
            val serviceIntent = Intent()
            serviceIntent.action = ACTION_CUSTOM_TABS_CONNECTION
            serviceIntent.setPackage(info.activityInfo.packageName)
            // Check if any package also resolves the Custom Tabs service.
            if (pm.resolveService(serviceIntent, 0) != null) {
                return true
            }
        }
        return false
    }
}
