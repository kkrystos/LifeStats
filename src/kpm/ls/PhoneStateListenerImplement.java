package kpm.ls;

import static kpm.ls.db.Const.KOLUMNA_1;
import static kpm.ls.db.Const.KOLUMNA_2;
import static kpm.ls.db.Const.KOLUMNA_3;
import static kpm.ls.db.Const.KOLUMNA_4;
import static kpm.ls.db.Const.KOLUMNA_5;
import static kpm.ls.db.Const.NAZWA_TABELI;
import static kpm.ls.db.Const.NAZWA_TABELI_2;
import kpm.ls.db.DataEvent;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class PhoneStateListenerImplement extends BroadcastReceiver {

	private Context mContext;
	private Toast myToast;
	private String incomingNr;
	private TelephonyManager telephonyManager;
	long end_time;
	long start_time;
	private String phonenumber;
	private String outgoingGodNumber;
	private DataEvent dataEvent;
	private boolean wychodzace = false;
	
	@Override
    public void onReceive(Context context, Intent intent) {
		dataEvent = new DataEvent(context);
        String action = intent.getAction();
        if(intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER) != null){
        	phonenumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
        }
        if(action.equalsIgnoreCase("android.intent.action.PHONE_STATE")){       
            if (intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(TelephonyManager.EXTRA_STATE_IDLE)) {
                	wezZdarzenie(context);       	
                }
            if (intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
                 }    
            }   
 }
	
	public void wezZdarzenie(Context context){
        String[] strFields = {
                android.provider.CallLog.Calls.NUMBER, 
                android.provider.CallLog.Calls.TYPE,
                android.provider.CallLog.Calls.CACHED_NAME,
                android.provider.CallLog.Calls.CACHED_NUMBER_TYPE,
                android.provider.CallLog.Calls.DURATION
                };
        String strOrder = android.provider.CallLog.Calls.DATE + " DESC"; 
        int typeCall =  CallLog.Calls.INCOMING_TYPE;
        String selection = "type = "+ typeCall + " AND new = 1";
        Cursor mCallCursor = context.getContentResolver().query(android.provider.CallLog.Calls.CONTENT_URI,strFields,
                null,
                null,
                strOrder
                );
        if(mCallCursor.moveToFirst()){
        	if(Integer.parseInt(mCallCursor.getString(1)) == 2 && Integer.parseInt(mCallCursor.getString(4)) > 0){
//        		 Toast.makeText(context,"OUTGOING\n"+mCallCursor.getString(4),Toast.LENGTH_SHORT).show();
        		dodajZdarzenie(NAZWA_TABELI,mCallCursor.getString(0), mCallCursor.getString(4), mCallCursor.getString(1), "outgoing");	
        	}
        	else if(Integer.parseInt(mCallCursor.getString(1)) == 1 && Integer.parseInt(mCallCursor.getString(4)) > 0){
//       		 Toast.makeText(context,"INCOMING\n"+mCallCursor.getString(4),Toast.LENGTH_SHORT).show();
       		dodajZdarzenie(NAZWA_TABELI_2,mCallCursor.getString(0), mCallCursor.getString(4), mCallCursor.getString(1), "incoming");	
       	}
        	} 
	}
	
    public void dodajZdarzenie(String nazwa_tabeli, String cena, String tytul, String opis, String ilosc) {
        ContentValues wartosci = new ContentValues();
        wartosci.put(KOLUMNA_1, cena);
        wartosci.put(KOLUMNA_2, tytul);
        wartosci.put(KOLUMNA_3, opis);
        wartosci.put(KOLUMNA_4, ilosc);
        wartosci.put(KOLUMNA_5, ilosc);
        dataEvent.getWritableDatabase().insert(nazwa_tabeli, null, wartosci);
        dataEvent.close();
    }
}
