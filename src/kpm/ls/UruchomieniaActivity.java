package kpm.ls;

import static kpm.ls.db.Const.KOLUMNA_2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static kpm.ls.db.Const.NAZWA_TABELI_7;
import kpm.ls.db.DataEvent;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class UruchomieniaActivity extends Activity{
	
	private String ilosc_uruchomien = "0";
	private DataBaseManager dataBaseManager;
	private DataEvent dataEvent;
	private SQLiteDatabase myDb;
	private TextView uruchomieniaLSTv;
	private TextView uruchomieniaTv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.uruchomienia_activity);
		
		dataEvent = new DataEvent(getApplicationContext());
		dataBaseManager = new DataBaseManager();
		
		uruchomieniaLSTv = (TextView) findViewById(R.id.uruchomieniaLSTv);
		uruchomieniaTv = (TextView) findViewById(R.id.uruchomieniaTv);

		
		

//		 for(int j=0; j<20; j++){
//			 al.add(am.getRunningTasks(1).get(j).topActivity.getPackageName());
//			 
//		 }
//	        uruchomieniaTv.setText(packageName);
	        
//	        am.getRunningTasks(10).get(1).topActivity.getPackageName();
//	        uruchomieniaTv.setText(""+al);

		
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
//		
		ilosc_uruchomien = dataBaseManager.SumujDane(dataEvent, myDb, KOLUMNA_2, NAZWA_TABELI_7);
		if(ilosc_uruchomien != null){
			uruchomieniaLSTv.setText("Aplikacjê LifeStats uruchomi³eœ:  "+ilosc_uruchomien+"  razy");	
		}
		
		ArrayList<String> al = new ArrayList<String>();
		
		ActivityManager am = (ActivityManager)this.getSystemService(ACTIVITY_SERVICE);
		List<ActivityManager.RunningAppProcessInfo> procList = am.getRunningAppProcesses();
		int i = 0;
		int ii = 0;
		
		for (ActivityManager.RunningAppProcessInfo proc : procList){
		    Log.i("TAGus", proc.processName);
//		    al.add(proc.processName + "\n");
		    i++;
		}
		al.add(""+i);
		
		
		 Log.i("TAGus", ""+i);
		 final   ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		 final List<RunningTaskInfo> recentTasks = activityManager.getRunningTasks(Integer.MAX_VALUE);

		     for (int j = 0; j < recentTasks.size(); j++) 
		     {
//		    	 if(recentTasks.get(j).baseActivity.toShortString().contains("com.android") == false){
		    		 Log.i("TAGus", "App: " +recentTasks.get(j).baseActivity.toShortString());         
				     ii++;
//		    	 }
		         
		     }
		     Log.i("TAGus",""+ii);
		     uruchomieniaTv.setText("Liczba uruchomionych aplikacji: "+ii);
//
	}
}