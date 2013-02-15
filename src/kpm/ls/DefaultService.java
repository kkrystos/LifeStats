package kpm.ls;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.widget.Toast;

public class DefaultService extends Service{
	
	private AppListener appListener;
	private InternetListener internetListener;
	//filtry uruchomionych aplikacji
	IntentFilter intentFilter1 = new IntentFilter("android.intent.action.PACKAGE_FIRST_LAUNCH");
	IntentFilter intentFilter2 = new IntentFilter("android.intent.category.LAUNCHER");
	IntentFilter intentFilter3 = new IntentFilter("android.intent.action.MAIN");
	//filtry do InternetListener
	IntentFilter intentFilterI1 = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
	IntentFilter intentFilterI2 = new IntentFilter("android.net.wifi.STATE_CHANGE");
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate() {
		Toast.makeText(getApplicationContext(), 
              "CallService uruchomiony", 
              Toast.LENGTH_SHORT).show();
	    super.onCreate();	  
	    internetListener = new InternetListener();
//	    registerReceiver(appListener, intentFilter1);
	    registerReceiver(appListener, intentFilter2);
	    registerReceiver(appListener, intentFilter3);
	    registerReceiver(internetListener, intentFilterI1);
	    registerReceiver(internetListener, intentFilterI2);
	}
	
	@Override
	public void onDestroy() {
	    super.onDestroy();
	    unregisterReceiver(appListener);
	    unregisterReceiver(internetListener);
	}
	
	

}
