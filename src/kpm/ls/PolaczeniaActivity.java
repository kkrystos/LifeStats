package kpm.ls;

import static kpm.ls.db.Const.KOLUMNA_2;
import static kpm.ls.db.Const.NAZWA_TABELI;
import static kpm.ls.db.Const.NAZWA_TABELI_2;
import kpm.ls.db.DataEvent;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class PolaczeniaActivity extends Activity{
	private DataEvent dataEvent;
	private SQLiteDatabase myDb;
	private DataBaseManager dataBaseManager;
    private String sumaWych ="0";
    private String sumaPrzych ="0";
    private int minuty;
    private int sekundy;
	private int count = 0;
    private int sumaInt;
	private TextView wychodzace_pol_Tv;
	private TextView przychodzace_pol_Tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.polaczenia_activity);
    	dataEvent = new DataEvent(this);  
    	dataBaseManager = new DataBaseManager();
    	
        wychodzace_pol_Tv = (TextView) findViewById(R.id.wychodzace_polTv);
        przychodzace_pol_Tv = (TextView) findViewById(R.id.przychodzace_polTv);
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
    	sumaWych = dataBaseManager.SumujDane(dataEvent, myDb, KOLUMNA_2, NAZWA_TABELI);
    	sumaPrzych = dataBaseManager.SumujDane(dataEvent, myDb, KOLUMNA_2, NAZWA_TABELI_2);
    	
    	if(sumaWych != null){
		    sumaInt = Integer.parseInt(sumaWych);
		    minuty = (sumaInt / 60);
		    sekundy = (sumaInt % 60);
		    wychodzace_pol_Tv.setText("Po³¹czenia wychodz¹ce: \n"+minuty + " min. " + sekundy +" sek.");
    	}
    	else{
    		wychodzace_pol_Tv.setText("Po³¹czenia wychodz¹ce: \n"+ "0" + " min. " + "0"+" sek.");
    	}
    	if(sumaPrzych != null){
		    sumaInt = Integer.parseInt(sumaPrzych);
		    minuty = (sumaInt / 60);
		    sekundy = (sumaInt % 60);
		    przychodzace_pol_Tv.setText("Po³¹czenia przychodz¹ce: \n"+minuty + " min. " + sekundy +" sek.");
    	}
    	else{
    		przychodzace_pol_Tv.setText("Po³¹czenia przychodz¹ce: \n"+ "0" + " min. " + "0"+" sek.");
    	}
		
	}
	}
