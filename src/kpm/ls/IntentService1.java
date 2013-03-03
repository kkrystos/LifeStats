package kpm.ls;

import static kpm.ls.db.Const.NAZWA_TABELI_10;
import kpm.ls.db.DataEvent;
import android.app.ActivityManager;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class IntentService1 extends IntentService{
	
	private int czasChrome = 0;
	private int czasBrowser = 0;
	static boolean  check = false;
	private DataEvent dataEvent;
	private DataBaseManager dataBaseManager;

	public IntentService1() {
		super("IntentService1");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
    	dataEvent = new DataEvent(getApplicationContext());  
    	dataBaseManager = new DataBaseManager();
		
		SimpleThread simpleThread = new SimpleThread("Browsers time Count");
		if(check == false){
			simpleThread.start();	
			check = true;
		}
	}
	
	class SimpleThread extends Thread {
	    public SimpleThread(String str) {
		super(str);
	    }
	    public void run() {
		for (long i = 0; i < 2139999999; i++) {
		    Log.i("TAGus", i + " " + getName());
		    ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		    String packageName = am.getRunningTasks(1).get(0).topActivity.getPackageName();
//		    String className = am.getRunningTasks(1).get(0).topActivity.getClassName();
		    if(packageName.contains("chrome")){
		    	czasChrome = czasChrome + 3;
			    Log.i("TAGus","pack: "+packageName);
			    Log.i("TAGus","czas: "+czasChrome);
			    dataBaseManager.dodajZdarzenie(dataEvent, NAZWA_TABELI_10, "czasChrome", ""+3, "czasBrowser", ""+0, "");
		    }
		    else if(packageName.contains("com.android.browser")){
		    	czasBrowser = czasBrowser +3;
			    Log.i("TAGus","pack: "+packageName);
			    Log.i("TAGus","czas: "+czasBrowser);
			    dataBaseManager.dodajZdarzenie(dataEvent, NAZWA_TABELI_10, "czasChrome", ""+0, "czasBrowser", ""+3, "");
		    }
	            try {
			sleep(3000);
		    } catch (InterruptedException e) {}
		}
		Log.i("TAGus", "DONE! " + getName());
	    }
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
}