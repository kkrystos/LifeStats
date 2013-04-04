package kpm.ls;

import static kpm.ls.db.Const.NAZWA_TABELI_9;


import java.io.IOException;
import java.util.Date;

import kpm.ls.db.DataEvent;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

public class DefaultService extends Service /*implements SensorEventListener */{

	private AppListener appListener;
	private InternetListener internetListener;
	
//    private static final String ACCOUNT_TYPE_GOOGLE = "com.google";
//    private static final String[] FEATURES_MAIL = {"service_mail"};
//    static Uri BASE_URI_STRING = Uri.parse("content://com.google.android.gm/");
	// filtry uruchomionych aplikacji
//	IntentFilter intentFilter1 = new IntentFilter("android.intent.action.PACKAGE_FIRST_LAUNCH");
//	IntentFilter intentFilter2 = new IntentFilter("com.android.chrome/com.google.android.apps.chrome.Main");
	
    IntentFilter filter1 = new IntentFilter(Intent.ACTION_SCREEN_ON);
    IntentFilter filter2 = new IntentFilter(Intent.ACTION_SCREEN_OFF);
	// filtry do InternetListener
	IntentFilter intentFilterI1 = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
	IntentFilter intentFilterI2 = new IntentFilter("android.net.wifi.STATE_CHANGE");

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
	private String account;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Toast.makeText(getApplicationContext(), "DefaultService uruchomiony",
				Toast.LENGTH_SHORT).show();
//		getLabelsGM();
//		Toast.makeText(getApplicationContext(), account,
//				Toast.LENGTH_SHORT).show();
		internetListener = new InternetListener();
		appListener = new AppListener();
		registerReceiver(appListener, filter1);
		registerReceiver(appListener, filter2);
		registerReceiver(internetListener, intentFilterI1);
		registerReceiver(internetListener, intentFilterI2);
		Intent intentService1 = new Intent(this, IntentService1.class);
//		intentService1.putExtra("account", account);
		startService(intentService1);

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
	
//	private void getLabelsGM(){
//		
//		AccountManager.get(this).getAccountsByTypeAndFeatures(ACCOUNT_TYPE_GOOGLE, FEATURES_MAIL,
//                new AccountManagerCallback<Account[]>() {
//                    @Override
//                    public void run(AccountManagerFuture<Account[]> future) {
//                        Account[] accounts = null;
//                        try {
//                            accounts = future.getResult();
//                        } catch (OperationCanceledException oce) {
//                        } catch (IOException ioe) {
//                        } catch (AuthenticatorException ae) {
//                        }
////                        onAccountResults(accounts);
//                        if (accounts != null && accounts.length > 0) {
//                            final String account = accounts[0].name;
////                            account = accounts[0].name;
//                            final Bundle args = new Bundle();
//                            args.putString("account", account);
//                            
//                        }
//                    }
//                }, null /* handler */);
//	}

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
