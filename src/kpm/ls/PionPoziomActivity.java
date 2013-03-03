package kpm.ls;

import static kpm.ls.db.Const.KOLUMNA_2;
import static kpm.ls.db.Const.KOLUMNA_3;
import static kpm.ls.db.Const.NAZWA_TABELI_9;
import kpm.ls.db.DataEvent;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class PionPoziomActivity extends Activity {
	
	private TextView pionTv;
	private TextView poziomTv;
	private DataEvent dataEvent;
	private DataBaseManager dataBaseManager;
	private SQLiteDatabase myDb;
	private String pionString = "";
	private String poziomString = "";
	private int pion = 0;
	private int poziom = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pionpoziom_activity);
    	dataEvent = new DataEvent(this);  
    	dataBaseManager = new DataBaseManager();
		
		pionTv = (TextView) findViewById(R.id.pionTv);
		poziomTv = (TextView) findViewById(R.id.poziomTv);
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		pionString = dataBaseManager.SumujDane(dataEvent, myDb, KOLUMNA_2, NAZWA_TABELI_9);
		poziomString = dataBaseManager.SumujDane(dataEvent, myDb, KOLUMNA_3, NAZWA_TABELI_9);
		
//    	if(pionString != null){
//    		pion = Integer.parseInt(pionString);
////    		ladowanie_min_int = (ladowanie_sek_int / 60);
////    		ladowanie_sekund_int = (ladowanie_sek_int % 60);
//    		pionTv.setText("W pionie: \n"+pion + " sek.");
//    	}
////    	else{
////    		pionTv.setText("W pionie: \n"+ "0 sek.");
////    	}
//    	if(poziomString != null){
//    		poziom = Integer.parseInt(poziomString);
////    		ladowanie_min_int = (ladowanie_sek_int / 60);
////    		ladowanie_sekund_int = (ladowanie_sek_int % 60);
//    		poziomTv.setText("W poziomie: \n"+poziom + " sek.");
//    	}
    	
    	if(pionString != null && poziomString != null){
    		pion = Integer.parseInt(pionString);
    		poziom = Integer.parseInt(poziomString);
    		
    		double suma = pion + poziom;
    		double procentPion = (pion/suma)*100;
//    		double procentPoziom = (poziom/suma)*100;
    		int a = (int)procentPion;
    		int b = 100-a;
    		pionTv.setText("W pionie: "+ a + " %");
    		poziomTv.setText("W poziomie: "+ b+ " %");
    	}
	}
}
