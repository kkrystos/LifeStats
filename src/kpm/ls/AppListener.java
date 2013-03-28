package kpm.ls;

import static kpm.ls.db.Const.NAZWA_TABELI_13;

import java.util.Date;

import kpm.ls.db.DataEvent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AppListener extends BroadcastReceiver {

	private DataEvent dataEvent;
	private DataBaseManager dataBaseManager;
	long start = 0;
	long stop = 0;
	long wynik = 0;
	int start_seconds=0;
	int stop_seconds;

	@Override
	public void onReceive(Context context, Intent intent) {
		dataEvent = new DataEvent(context);
		dataBaseManager = new DataBaseManager();

		if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
//			Toast.makeText(context, "ODBLOKOWANY", Toast.LENGTH_LONG).show();
			Log.i("TAGus", "ODBLOKOWANY");

			start = new Date().getTime();

		} else if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
//			Toast.makeText(context, "ZABLOKOWANY", Toast.LENGTH_LONG).show();
			Log.i("TAGus", "ZABLOKOWANY");
			 if(start > 0 ){
				 stop = new Date().getTime();	
				 wynik = stop - start; 
				int wynik_sec = (int) (wynik / 1000);             
	             dataBaseManager.dodajZdarzenie(dataEvent, NAZWA_TABELI_13, "smartfonON", ""+wynik_sec, "", "", "");
			 }
             start = 0;
             stop=0; 
		}
	}
}
