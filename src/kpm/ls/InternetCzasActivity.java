package kpm.ls;

import static kpm.ls.db.Const.KOLUMNA_2;
import static kpm.ls.db.Const.NAZWA_TABELI_8;
import kpm.ls.db.DataEvent;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class InternetCzasActivity extends Activity{
	private TextView internetCzasTv;
	private DataEvent dataEvent;
	private DataBaseManager dataBaseManager;
	private SQLiteDatabase myDb;
	private String internetCzas = "0";
	private int internetCzas_sek_int = 0;
	private int internetCzas_sekund_int = 0;
	private int internetCzas_min_int = 0;
	private int internetCzas_godz_int = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.internet_czas_activity);
		
    	dataEvent = new DataEvent(this);  
    	dataBaseManager = new DataBaseManager();

		internetCzasTv = (TextView) findViewById(R.id.internetCzasTv);
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		internetCzas = dataBaseManager.SumujDane(dataEvent, myDb, KOLUMNA_2, NAZWA_TABELI_8);
		
    	if(internetCzas != null){
    		internetCzas_sek_int = Integer.parseInt(internetCzas);
    		internetCzas_min_int = (internetCzas_sek_int / 60);
    		internetCzas_godz_int = (internetCzas_sek_int / (60*60));
    		internetCzas_sekund_int = (internetCzas_sek_int % 60);
    		internetCzasTv.setText("Czas spêdzony w Internecie: \n"+internetCzas_godz_int+" godz. "
    		+(internetCzas_min_int%60) + " min. " + internetCzas_sekund_int +" sek.");
    	}
    	else{
    		internetCzasTv.setText("Czas spêdzony w Internecie: \n"+ "0 godz. 0 min. 0 sec");
    	}
	}
}
