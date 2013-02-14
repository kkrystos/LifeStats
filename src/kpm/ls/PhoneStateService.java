package kpm.ls;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.widget.Toast;

public class PhoneStateService extends Service{
	
	private Toast myToast;
	private PhoneStateListenerImplement phoneStateListener;
	private BatteryListener batteryListener;
	IntentFilter intentFilter1 = new IntentFilter("android.intent.action.PHONE_STATE");
	IntentFilter intentFilter2 = new IntentFilter("android.intent.action.NEW_OUTGOING_CALL");
//	IntentFilter intentFilter3 = new IntentFilter("android.intent.action.BATTERY_CHANGED");
//	IntentFilter intentFilter4 = new IntentFilter("android.intent.action.ACTION_UMS_CONNECTED");
//	IntentFilter intentFilter5 = new IntentFilter("android.intent.action.ACTION_UMS_DISCONNECTED");
//	IntentFilter intentFilter6 = new IntentFilter("android.intent.action.UMS_CONNECTED");
//	IntentFilter intentFilter7 = new IntentFilter("android.intent.action.UMS_DISCONNECTED");
	IntentFilter intentFilter8 = new IntentFilter("android.intent.action.ACTION_POWER_CONNECTED");
	IntentFilter intentFilter9 = new IntentFilter("android.intent.action.ACTION_POWER_DISCONNECTED");

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate() {
	    super.onCreate();
	    phoneStateListener = new PhoneStateListenerImplement();
	    batteryListener = new BatteryListener();
	    registerReceiver(phoneStateListener, intentFilter1);
	    registerReceiver(phoneStateListener, intentFilter2);
//	    registerReceiver(batteryListener, intentFilter3);
//	    registerReceiver(batteryListener, intentFilter4);
//	    registerReceiver(batteryListener, intentFilter5);
//	    registerReceiver(batteryListener, intentFilter6);
//	    registerReceiver(batteryListener, intentFilter7);
	    registerReceiver(batteryListener, intentFilter8);
	    registerReceiver(batteryListener, intentFilter9);
	    myToast = Toast.makeText(getApplicationContext(), 
	                             "Us³uga PO£¥CZENIA zosta³a uruchomiona", 
	                             Toast.LENGTH_SHORT);
	    myToast.show();
	}
	
	@Override
	public void onDestroy() {
	    
	    myToast.setText("Us³uga PO£¥CZENIA zosta³a zatrzymana");
	    myToast.show();
	    unregisterReceiver(this.phoneStateListener);
	    unregisterReceiver(this.batteryListener);

	    super.onDestroy();
	}
	

}
