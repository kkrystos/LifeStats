package kpm.ls;

import kpm.ls.db.DataEvent;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.RemoteViews;

public class AppWidget extends AppWidgetProvider {

	private DataEvent dataEvent;
	private SQLiteDatabase myDb;
	private String cena;
	private String suma = "0";
	private String sumaPrzych = "0";
	private Cursor kursor;
	private Cursor k;
	private int minuty;
	private int sekundy;
	private int sumaInt;

	@Override
	public void onUpdate(Context ctxt, AppWidgetManager mgr, int[] appWidgetIds) {

		dataEvent = new DataEvent(ctxt);
		// k = wezSume();
		// pokazSume(k);
		// pokazSumePrzychodzacych(kursor);
		ComponentName me = new ComponentName(ctxt, AppWidget.class);

		mgr.updateAppWidget(me, buildUpdate(ctxt, appWidgetIds));
	}

	private RemoteViews buildUpdate(Context ctxt, int[] appWidgetIds) {
		RemoteViews updateViews = new RemoteViews(ctxt.getPackageName(),
				R.layout.widget);

		Intent i = new Intent(ctxt, AppWidget.class);

		i.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
		i.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

		PendingIntent pi = PendingIntent.getBroadcast(ctxt, 0, i,
				PendingIntent.FLAG_UPDATE_CURRENT);
		if (suma != null) {
			sumaInt = Integer.parseInt(suma);
			minuty = (sumaInt / 60);
			sekundy = (sumaInt % 60);
			updateViews.setTextViewText(R.id.textView1, minuty + " min. "
					+ sekundy + " sek.");
		} else {
			updateViews.setTextViewText(R.id.textView1, "0" + " min. " + "0"
					+ " sek.");
		}

		if (sumaPrzych != null) {
			sumaInt = Integer.parseInt(sumaPrzych);
			minuty = (sumaInt / 60);
			sekundy = (sumaInt % 60);
			updateViews.setTextViewText(R.id.wych_pods, minuty + " min. "
					+ sekundy + " sek.");
		} else {
			updateViews.setTextViewText(R.id.wych_pods, "0" + " min. " + "0"
					+ " sek.");
		}

		return (updateViews);
	}

	// public Cursor wezSume() {
	// myDb = dataEvent.getReadableDatabase();
	// return myDb.rawQuery("select SUM(czas) from zdarzenia", null);
	// }
	// public void pokazSume(Cursor kursor) {
	// if(kursor.moveToFirst()) {
	// suma = kursor.getString(0);
	// }
	// dataEvent.close();
	// }
	// public Cursor wezSumePrzychodzacych() {
	// myDb = dataEvent.getReadableDatabase();
	// return myDb.rawQuery("select SUM(czas) from wychodzace_polaczenia",
	// null);
	// }
	// public void pokazSumePrzychodzacych(Cursor kursor) {
	// if(kursor.moveToFirst()) {
	// sumaPrzych = kursor.getString(0);
	// }
	// dataEvent.close();
	// }

}