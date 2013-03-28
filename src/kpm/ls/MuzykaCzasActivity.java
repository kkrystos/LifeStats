package kpm.ls;

import static kpm.ls.db.Const.KOLUMNA_2;
import static kpm.ls.db.Const.NAZWA_TABELI_12;
import kpm.ls.db.DataEvent;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class MuzykaCzasActivity extends Activity{
	
	private DataEvent dataEvent;
	private DataBaseManager dataBaseManager;
	private SQLiteDatabase myDb;
	
	private TextView muzykaCzasTv;
	private String muzykaCzas_sek = "0";
	private int muzykaCzas_sek_int = 0;
	private int muzykaCzas_sekund_int = 0;
	private int muzykaCzas_min_int = 0;
	private int muzykaCzas_godz_int = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.muzyka_activity);
		
		muzykaCzasTv = (TextView) findViewById(R.id.muzykaCzasTv);
		
    	dataEvent = new DataEvent(this);  
    	dataBaseManager = new DataBaseManager();
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		muzykaCzas_sek = dataBaseManager.SumujDane(dataEvent, myDb, KOLUMNA_2, NAZWA_TABELI_12);
		
    	if(muzykaCzas_sek != null){
    		muzykaCzas_sek_int = Integer.parseInt(muzykaCzas_sek);
    		muzykaCzas_min_int = (muzykaCzas_sek_int / 60);
    		muzykaCzas_godz_int = muzykaCzas_min_int/60;
    		muzykaCzas_sekund_int = (muzykaCzas_sek_int % 60);
    		muzykaCzasTv.setText("S³ucha³eœ muzyki przez: \n"+muzykaCzas_godz_int+" godz. "+(muzykaCzas_min_int%60) + " min. " + muzykaCzas_sekund_int +" sek.");
    	}
	}
}
