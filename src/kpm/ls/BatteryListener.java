package kpm.ls;

import static kpm.ls.db.Const.NAZWA_TABELI_6;

import java.util.Calendar;
import java.util.Date;

import kpm.ls.db.DataEvent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class BatteryListener extends BroadcastReceiver{
	long lDateTime;
	Calendar lCDateTime;
	long lCalendarMilSec;
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
//    	
		 if(intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)){
             Toast.makeText(context, "connected", Toast.LENGTH_SHORT).show();
//             Log.i("", "onReceive");
             start = new Date().getTime();	             
     }
//		 else if(intent.getAction().equals(Intent.ACTION_UMS_DISCONNECTED)){
		 else{
             Toast.makeText(context, "disconnected", Toast.LENGTH_SHORT).show();
			 if(start > 0 ){
				 stop = new Date().getTime();	
				 wynik = stop - start; 
//				int wynik_sec = (int) (wynik / 1000) % 60 ;
				int wynik_sec = (int) (wynik / 1000);
	             Toast.makeText(context, "³adowa³eœ: \n"+wynik_sec + "sekund", Toast.LENGTH_SHORT).show(); 	             
	             dataBaseManager.dodajZdarzenie(dataEvent, NAZWA_TABELI_6, "ladowanie_usb", ""+wynik_sec, "", "", "");
			 }
             start = 0;
             stop=0;      
     }
	}
//	Toast myToast;
//	@Override
//	public void onReceive(Context context, Intent intent) {
//		// TODO Auto-generated method stub
//		if(intent.getAction().equals(Intent.ACTION_MEDIA_MOUNTED)){
//		
//		    myToast = Toast.makeText(context, 
//                    "USB connected", 
//                    Toast.LENGTH_SHORT);
//		    myToast.show();
//		}
//		else {
//		    myToast = Toast.makeText(context, 
//                    "USB disconnected", 
//                    Toast.LENGTH_SHORT);
//		    myToast.show();
//		}
//
//		
//	}
}
