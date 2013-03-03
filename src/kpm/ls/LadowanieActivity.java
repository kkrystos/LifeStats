package kpm.ls;

import static kpm.ls.db.Const.KOLUMNA_2;
import static kpm.ls.db.Const.NAZWA_TABELI_6;
import kpm.ls.db.DataEvent;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class LadowanieActivity extends Activity{
	private TextView ladowanieTv;
	private String ladowanie_sek = "0";
	private int ladowanie_sek_int = 0;
	private int ladowanie_sekund_int = 0;
	private int ladowanie_min_int = 0;
	private int ladowanie_godz_int = 0;
	private DataEvent dataEvent;
	private DataBaseManager dataBaseManager;
	private SQLiteDatabase myDb;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.ladowanie_activity);
	    
	    ladowanieTv = (TextView) findViewById(R.id.ladowanieTv);
    	dataEvent = new DataEvent(this);  
    	dataBaseManager = new DataBaseManager();
	    
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		ladowanie_sek = dataBaseManager.SumujDane(dataEvent, myDb, KOLUMNA_2, NAZWA_TABELI_6);
		
    	if(ladowanie_sek != null){
    		ladowanie_sek_int = Integer.parseInt(ladowanie_sek);
    		ladowanie_min_int = (ladowanie_sek_int / 60);
    		ladowanie_godz_int = ladowanie_min_int/60;
    		ladowanie_sekund_int = (ladowanie_sek_int % 60);
		    ladowanieTv.setText("£adowa³eœ mnie: \n"+(ladowanie_godz_int%60)+" godz. "+(ladowanie_min_int%60) + " min. " + ladowanie_sekund_int +" sek.");
    	}
    	else{
    		ladowanieTv.setText("£adowa³eœ mnie: \n"+ "0" + " min. " + "0"+" sek.");
    	}
	}
	}