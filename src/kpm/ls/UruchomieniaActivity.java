package kpm.ls;

import static kpm.ls.db.Const.KOLUMNA_2;
import static kpm.ls.db.Const.KOLUMNA_4;
import java.util.List;
import static kpm.ls.db.Const.NAZWA_TABELI_7;
import static kpm.ls.db.Const.NAZWA_TABELI_10;
import kpm.ls.db.DataEvent;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class UruchomieniaActivity extends Activity{
	
	private String ilosc_uruchomien = "0";
	private String czas_chrome = "0";
	private String czas_browser = "0";
	private DataBaseManager dataBaseManager;
	private DataEvent dataEvent;
	private SQLiteDatabase myDb;
	private TextView uruchomieniaLSTv;
	private TextView uruchomieniaTv;
	private TextView chromeCzasTv;
	private TextView browserCzasTv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.uruchomienia_activity);
		
		dataEvent = new DataEvent(getApplicationContext());
		dataBaseManager = new DataBaseManager();
		
		uruchomieniaLSTv = (TextView) findViewById(R.id.uruchomieniaLSTv);
		uruchomieniaTv = (TextView) findViewById(R.id.uruchomieniaTv);
		chromeCzasTv = (TextView) findViewById(R.id.chromeCzasTv);
		browserCzasTv = (TextView) findViewById(R.id.browserCzasTv);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();	
		ilosc_uruchomien = dataBaseManager.SumujDane(dataEvent, myDb, KOLUMNA_2, NAZWA_TABELI_7);
		czas_chrome = dataBaseManager.SumujDane(dataEvent, myDb, KOLUMNA_2, NAZWA_TABELI_10);
		czas_browser = dataBaseManager.SumujDane(dataEvent, myDb, KOLUMNA_4, NAZWA_TABELI_10);
		if(ilosc_uruchomien != null){
			uruchomieniaLSTv.setText("Aplikacjê LifeStats uruchomi³eœ:  "+ilosc_uruchomien+"  razy");	
		}
		if(czas_chrome != null){
			int godzina;
			int minuta;
			int sekunda;
			sekunda = Integer.parseInt(czas_chrome);
			minuta = sekunda/60;
			godzina= minuta/60;
			chromeCzasTv.setText("Z przegl¹darki Chrome korzysta³eœ: "+godzina+" godz. " +(minuta%60) +" min. "+(sekunda%60)+"  sek.");	
		}
		if(czas_browser != null){
			int godzina;
			int minuta;
			int sekunda;
			sekunda = Integer.parseInt(czas_browser);
			minuta = sekunda/60;
			godzina= minuta/60;
			browserCzasTv.setText("Z domyœlnej przegl¹darki korzysta³eœ: " +godzina+" godz. " +(minuta%60) +" min. "+(sekunda%60)+"  sek.");	
		}
		
		 int iloscUruchomienAll = 0;	
		 final   ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		 final List<RunningTaskInfo> recentTasks = activityManager.getRunningTasks(Integer.MAX_VALUE);

		     for (int j = 0; j < recentTasks.size(); j++) 
		     {
		    	 iloscUruchomienAll++;	         
		     }
		     Log.i("TAGus",""+iloscUruchomienAll);
		     uruchomieniaTv.setText("Liczba uruchomionych aplikacji: "+iloscUruchomienAll);
	}
}