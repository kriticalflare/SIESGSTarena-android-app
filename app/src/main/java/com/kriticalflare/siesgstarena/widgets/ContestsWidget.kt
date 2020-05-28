package com.kriticalflare.siesgstarena.widgets

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.RemoteViews
import com.kriticalflare.siesgstarena.R
import com.kriticalflare.siesgstarena.ui.MainActivity

/**
 * Implementation of App Widget functionality.
 */
class ContestsWidget : AppWidgetProvider() {
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent!!.action.equals(CONTEST_WIDGET_ACTION)) {
            val openActivityIntent = Intent(context, MainActivity::class.java)
            openActivityIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context?.startActivity(openActivityIntent)
        }
        super.onReceive(context, intent)
    }

    companion object {
        const val CONTEST_WIDGET_ACTION = "com.kriticalflare.siesgstarena.CONTEST_WIDGET_ACTION"
    }
}

internal fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {

    val serviceIntent = Intent(context, ContestsWidgetService::class.java)
    serviceIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
    serviceIntent.data = Uri.parse(serviceIntent.toUri(Intent.URI_INTENT_SCHEME))

    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.contests_widget)
    views.setRemoteAdapter(R.id.contest_widget_listview, serviceIntent)
    views.setEmptyView(R.id.contest_widget_listview, R.id.contest_widget_emptyview)

    val clickIntent = Intent(context, ContestsWidget::class.java)
    clickIntent.action = ContestsWidget.CONTEST_WIDGET_ACTION
    clickIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
    val clickPendingIntent = PendingIntent.getBroadcast(
        context, 0, clickIntent,
        PendingIntent.FLAG_UPDATE_CURRENT
    )
    views.setPendingIntentTemplate(R.id.contest_widget_listview, clickPendingIntent)

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}
