package kpm.ls;

import static kpm.ls.db.Const.NAZWA_TABELI_9;


import java.util.Date;

import kpm.ls.db.DataEvent;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.IBinder;
import android.widget.Toast;

public class DefaultService extends Service /*implements SensorEventListener */{

	private AppListener appListener;
	private InternetListener internetListener;
	// filtry uruchomionych aplikacji
	IntentFilter intentFilter1 = new IntentFilter(
			"android.intent.action.PACKAGE_FIRST_LAUNCH");
	IntentFilter intentFilter2 = new IntentFilter(
			"com.android.chrome/com.google.android.apps.chrome.Main");
	// IntentFilter intentFilter3 = new
	// IntentFilter("android.intent.action.MAIN");
	// filtry do InternetListener
	IntentFilter intentFilterI1 = new IntentFilter(
			"android.net.conn.CONNECTIVITY_CHANGE");
	IntentFilter intentFilterI2 = new IntentFilter(
			"android.net.wifi.STATE_CHANGE");

	private int orientationInit = 0;
	long startPion = 0;
	long stopPion = 0;
	long startPoziom = 0;
	long stopPoziom = 0;
	long wynikPion = 0;
	long wynikPoziom = 0;
	private DataEvent dataEvent;
	private DataBaseManager dataBaseManager;
	private SQLiteDatabase myDb;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		Toast.makeText(getApplicationContext(), "DefaultService uruchomiony",
				Toast.LENGTH_SHORT).show();
		super.onCreate();
		internetListener = new InternetListener();
		// registerReceiver(appListener, intentFilter1);
		registerReceiver(appListener, intentFilter2);
		// registerReceiver(appListener, intentFilter3);
		registerReceiver(internetListener, intentFilterI1);
		registerReceiver(internetListener, intentFilterI2);
		startService(new Intent(this, IntentService1.class));

		dataEvent = new DataEvent(getApplicationContext());
		dataBaseManager = new DataBaseManager();

		orientationInit = getResources().getConfiguration().orientation;

		if (orientationInit == 1) {
			// Toast.makeText(getApplicationContext(), "ORIENTATION_PORTRAIT",
			// Toast.LENGTH_SHORT).show();
			startPion = new Date().getTime();
		} else if (orientationInit == 2) {
			// Toast.makeText(getApplicationContext(), "ORIENTATION_LANDSCAPE",
			// Toast.LENGTH_SHORT).show();
			startPoziom = new Date().getTime();
		}
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		int orientation = newConfig.orientation;

		switch (orientation) {
		case Configuration.ORIENTATION_LANDSCAPE:

			stopPion = new Date().getTime();
			startPoziom = new Date().getTime();
			wynikPion = (stopPion - startPion) / 1000;
			// Toast.makeText(getApplicationContext(), "ORIENTATION_PORTRAIT: "+
			// wynikPion, Toast.LENGTH_SHORT).show();
			dataBaseManager.dodajZdarzenie(dataEvent, NAZWA_TABELI_9,
					"pion_poziom", "" + wynikPion, "0", "", "");
			break;
		case Configuration.ORIENTATION_PORTRAIT:

			stopPoziom = new Date().getTime();
			startPion = new Date().getTime();
			wynikPoziom = (stopPoziom - startPoziom) / 1000;
			// Toast.makeText(getApplicationContext(),
			// "ORIENTATION_LANDSCAPE: "+ wynikPoziom,
			// Toast.LENGTH_SHORT).show();
			dataBaseManager.dodajZdarzenie(dataEvent, NAZWA_TABELI_9,
					"pion_poziom", "0", "" + wynikPoziom, "", "");
			break;
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		unregisterReceiver(appListener);
		unregisterReceiver(internetListener);
	}

//	@Override
//	public void onAccuracyChanged(Sensor sensor, int accuracy) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void onSensorChanged(SensorEvent event) {
//		// TODO Auto-generated method stub
//		
//	}
}
