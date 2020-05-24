package com.kriticalflare.siesgstarena.widgets

import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.kriticalflare.siesgstarena.R
import com.kriticalflare.siesgstarena.database.ContestsDao
import com.kriticalflare.siesgstarena.models.Contest
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.threeten.bp.Instant
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter
import java.util.*

class ContestsWidgetService : RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent?): RemoteViewsFactory {
        return ContestsWidgetItemFactory(applicationContext, intent)
    }

    class ContestsWidgetItemFactory(private val context: Context, private val intent: Intent?) :
        RemoteViewsFactory, KoinComponent {
        private val contestsDao: ContestsDao by inject()

        private val appWidgetId =
            intent?.getIntExtra(
                AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID
            )


        var data: List<Contest>? = null

        override fun onCreate() {

        }

        override fun getLoadingView(): RemoteViews {
            return RemoteViews(context.packageName, R.layout.contest_widget_loadview)
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun onDataSetChanged() {
            data = contestsDao.getContestsWidget().reversed().take(10)
        }

        override fun hasStableIds(): Boolean {
            return true
        }

        override fun getViewAt(position: Int): RemoteViews {
            val dateFormatter = DateTimeFormatter.ofPattern(
                "EEE dd/MM/yy H:mm a",
                Locale.ENGLISH
            )

            val formattedStartsAt: String = Instant.parse(data?.get(position)?.startsAt)
                .atZone(ZoneId.systemDefault())
                .format(dateFormatter)

            val remoteViews = RemoteViews(context.packageName, R.layout.contest_widget_item)
            remoteViews.setTextViewText(R.id.contest_widget_title, data?.get(position)?.name)

            remoteViews.setTextViewText(R.id.contest_widget_time, "Starts at: $formattedStartsAt")

            val onClickIntent = Intent()
            remoteViews.setOnClickFillInIntent(R.id.contest_widget_item_root,onClickIntent)

            return remoteViews
        }

        override fun getCount(): Int {
            return data?.count() ?: 0
        }

        override fun getViewTypeCount(): Int {
            return 1
        }

        override fun onDestroy() {

        }

    }

}