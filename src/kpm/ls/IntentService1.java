package kpm.ls;

import static kpm.ls.db.Const.NAZWA_TABELI_10;

import java.util.List;

import kpm.ls.db.DataEvent;

import android.app.ActivityManager;
import android.app.IntentService;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

public class IntentService1 extends IntentService{
	
	private int czasChrome = 0;
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
		
		SimpleThread simpleThread = new SimpleThread("Chrome Count");
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
		for (int i = 0; i < 1000; i++) {
		    Log.i("TAGus", i + " " + getName());
		    
		    
		    final   ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
			 final List<RunningTaskInfo> recentTasks = activityManager.getRunningTasks(Integer.MAX_VALUE);

			     for (int j = 0; j < recentTasks.size(); j++) 
			     {
			    	 if(recentTasks.get(j).baseActivity.toShortString().contains("chrome") == true){
			    		 czasChrome = czasChrome + 3;
			    		 dataBaseManager.dodajZdarzenie(dataEvent, NAZWA_TABELI_10, "czasChrome", ""+3, "", "", "");
			    		 Log.i("TAGus","chrme odpalony przez: "+czasChrome+" sec.");
			    	 }		         
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
