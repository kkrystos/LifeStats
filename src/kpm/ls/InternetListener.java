package kpm.ls;

import static kpm.ls.db.Const.NAZWA_TABELI_6;

import java.util.Date;

import kpm.ls.db.DataEvent;
import static kpm.ls.db.Const.NAZWA_TABELI_8;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.Toast;

public class InternetListener extends BroadcastReceiver{

	private WifiInfo wifiInfo;
	long start = 0;
	long stop = 0;
	long wynik = 0;
	int start_seconds=0;
	int stop_seconds;
	private DataEvent dataEvent;
	private DataBaseManager dataBaseManager;
	private SQLiteDatabase myDb;
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
    	dataEvent = new DataEvent(context);  
    	dataBaseManager = new DataBaseManager();
		//Dostêpnoœæ internetu ogólnie
		if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
			NetworkInfo networkInfo = intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
			
			if (networkInfo.isConnected()) {
				Log.i("NET","NET is connected: " + String.valueOf(networkInfo));
//				Toast.makeText(context,"NET connected" ,Toast.LENGTH_SHORT).show();		
				if(networkInfo.getType() == ConnectivityManager.TYPE_WIFI){
//					Log.i("NET","Wbi³o siê wifi");
					 if(start > 0 ){
						 stop = new Date().getTime();	
						 wynik = stop - start; 
						 int wynik_sec = (int) (wynik / 1000);
							Log.i("NET","Wynik: " + wynik_sec + "sec");
//							Toast.makeText(context,"Wynik: " + wynik_sec + "sec" ,Toast.LENGTH_SHORT).show();
							 dataBaseManager.dodajZdarzenie(dataEvent, NAZWA_TABELI_8, "internet_on", ""+wynik_sec, "", "", "");
					 }
		             start = 0;
		             stop=0; 
				}
				start = new Date().getTime();	
				
			}
			else if (!networkInfo.isConnected()) {
//				Log.i("NET","NET is disconnected: " + String.valueOf(networkInfo));
//				Toast.makeText(context,"NET disconnected" ,Toast.LENGTH_SHORT).show();
				 if(start > 0 ){
					 stop = new Date().getTime();	
					 wynik = stop - start; 
					 int wynik_sec = (int) (wynik / 1000);
						Log.i("NET","Wynik: " + wynik_sec + "sec");
//						Toast.makeText(context,"Wynik: " + wynik_sec + "sec" ,Toast.LENGTH_SHORT).show();
					 dataBaseManager.dodajZdarzenie(dataEvent, NAZWA_TABELI_8, "internet_on", ""+wynik_sec, "", "", "");	 
				 }
	             start = 0;
	             stop=0;  
			}
		}
	}

}
