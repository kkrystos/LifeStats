package kpm.ls;

import static kpm.ls.db.Const.KOLUMNA_2;
import static kpm.ls.db.Const.NAZWA_TABELI_13;
import kpm.ls.db.DataEvent;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class SmartfonCzasActivity extends Activity{
	
	private DataEvent dataEvent;
	private DataBaseManager dataBaseManager;
	private SQLiteDatabase myDb;
	
	private TextView smartfonCzasTv;
	private String smartfonCzas_sek = "0";
	private int smartfonCzas_sek_int = 0;
	private int smartfonCzas_sekund_int = 0;
	private int smartfonCzas_min_int = 0;
	private int smartfonCzas_godz_int = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.smartfon_activity);
		
		smartfonCzasTv = (TextView) findViewById(R.id.smartfonCzasTv);
		
    	dataEvent = new DataEvent(this);  
    	dataBaseManager = new DataBaseManager();
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		smartfonCzas_sek = dataBaseManager.SumujDane(dataEvent, myDb, KOLUMNA_2, NAZWA_TABELI_13);
		
    	if(smartfonCzas_sek != null){
    		smartfonCzas_sek_int = Integer.parseInt(smartfonCzas_sek);
    		smartfonCzas_min_int = (smartfonCzas_sek_int / 60);
    		smartfonCzas_godz_int = smartfonCzas_min_int/60;
    		smartfonCzas_sekund_int = (smartfonCzas_sek_int % 60);
    		smartfonCzasTv.setText("U¿ywa³eœ smartfonu przez: \n"+smartfonCzas_godz_int+" godz. "+(smartfonCzas_min_int%60) + " min. " + smartfonCzas_sekund_int +" sek.");
    	}
	}
}
