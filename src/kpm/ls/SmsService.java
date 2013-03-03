package kpm.ls;
import static kpm.ls.db.Const.NAZWA_TABELI_4;
import static kpm.ls.db.Const.NAZWA_TABELI_5;

import java.util.Date;
import kpm.ls.SmsService.MissedCallsContentObserver;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import android.widget.Toast;
import kpm.ls.db.DataEvent;

public class SmsService extends Service{
	
	private Toast myToast;
	private SmsListener smsListener;
	IntentFilter intentFilter1 = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
	IntentFilter intentFilter2 = new IntentFilter("android.provider.Telephony.SMS_SEND");
	
	private String smsNumber;
    private String smsNumberSub;
    private  String numerDB;
	private String id;
    private MissedCallsContentObserver mcco;
    private Handler mHandler;
    private int count =0;
    private WakeLock wl;
    private AlarmManager am;
    private Intent i;
    private PendingIntent pi;
    private String values;
    private String intervals;
    private int ivalues;
    private int iintervals;
    private DataEvent dataEvent;
	DataBaseManager dataBaseManager;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate() {
	    super.onCreate();
	    smsListener = new SmsListener();
	    registerReceiver(smsListener, intentFilter1);
	    registerReceiver(smsListener, intentFilter2);
//	    myToast = Toast.makeText(getApplicationContext(), 
//	                             "Us³uga SMS zosta³a uruchomiona", 
//	                             Toast.LENGTH_SHORT);
//	    myToast.show();
	    
	    mcco = new MissedCallsContentObserver(new Handler());
	      getApplicationContext().getContentResolver().registerContentObserver(Uri.parse("content://mms-sms/sent"), true, mcco);
	      dataEvent = new DataEvent(getApplicationContext());
	      dataBaseManager = new DataBaseManager();
	}
	
	@Override
	public void onDestroy() {
	    super.onDestroy();
//	    myToast.setText("Us³uga SMS zosta³a zatrzymana");
	    myToast.show();
	    unregisterReceiver(this.smsListener);
	    getApplicationContext().getContentResolver().unregisterContentObserver(mcco);

	}
	class MissedCallsContentObserver extends ContentObserver
    {	
		int i = 0;
		boolean check = false;
		
    	long lastTimeofCall = 0L;
    	long lastTimeofUpdate = 0L;
    	long threshold_time = 2000;
    	
        public MissedCallsContentObserver(Handler h)
        {
            super(h);
            }
        	    @Override
        	    public void onChange(boolean selfChange) {
        	    	super.onChange(selfChange);
        	    	
        	        lastTimeofCall = System.currentTimeMillis();
        	        
        	        Log.i("onCAHANGE", "wynik : "+ (lastTimeofCall - lastTimeofUpdate));

        	        if(lastTimeofCall - lastTimeofUpdate > threshold_time){    	
            	    	Cursor cursor = getContentResolver().query(Uri.parse("content://sms"), null, null, null, null);
            	    	cursor.moveToFirst();
            	    	int dateColumn = cursor.getColumnIndex("date");
            			int bodyColumn = cursor.getColumnIndex("body");
            			int addressColumn = cursor.getColumnIndex("address");
            			int type = cursor.getColumnIndex("type");
            			String from = "0";
            			String to = cursor.getString(addressColumn);
            			String message = cursor.getString(bodyColumn);
            			String typ = cursor.getString(type);
            	    	 
            	    	   Date now = new Date(cursor.getLong(dateColumn));
//            	     	   Date currentDate = new Date(System.currentTimeMillis());
            	     	   
//            	     	   long czasSms = now.getTime();
//            	     	   long czasSystemu = currentDate.getTime();
//            	     		long czasSmsSec = czasSms/1000;
//            	     		long czasSysSec = czasSystemu/1000;
//            	     		Log.i("onCAHANGE", "Czas SMS: " + czasSmsSec);
//            	     		Log.i("onCAHANGE", "Czas SYSTEMU: " + czasSysSec);
//            	     		Toast.makeText(getApplicationContext(), "Typ: "+ typ, Toast.LENGTH_SHORT).show();
            	     		if(typ.equalsIgnoreCase("3")||typ.equalsIgnoreCase("6")){
//            	     			if(l2-l1 == 0){
//                	     			if(l2-l1 == 0 || l2-l1 == 1||l2-l1==2){
//                	     			if(czasSysSec - czasSmsSec == 0 || czasSysSec - czasSmsSec == 1||czasSysSec - czasSmsSec==2){
//                	     			if(czasSysSec - czasSmsSec == 0 || czasSysSec - czasSmsSec == 1){
                	     			Log.i("onCAHANGE", "dodano do bazki "+ to + " "+ message);
                	     			Toast.makeText(getApplicationContext(), "SMS wys³ano: "+ to+ " ", Toast.LENGTH_SHORT).show();
//                	     			Toast.makeText(getApplicationContext(), "Typ: "+ typ, Toast.LENGTH_SHORT).show();
                	     			dataBaseManager.dodajZdarzenie(dataEvent, NAZWA_TABELI_5, to, "1", message, ""+now.getDate(), "");
//                	     		}
            	     		}
        	        	
        	          lastTimeofUpdate = System.currentTimeMillis();
        	        }
        	    	

        	    }
        	    @Override
        	    public boolean deliverSelfNotifications() {
        	        return false;
        	    }        	
        	    
    }
	

}
