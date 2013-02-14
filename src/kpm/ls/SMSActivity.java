package kpm.ls;

import static kpm.ls.db.Const.KOLUMNA_2;
import static kpm.ls.db.Const.NAZWA_TABELI_4;
import static kpm.ls.db.Const.NAZWA_TABELI_5;
import kpm.ls.db.DataEvent;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class SMSActivity extends Activity{
    private String sumaSmsPrzych ="0";
    private String sumaSmsWych ="0";
	private DataEvent dataEvent;
	private DataBaseManager dataBaseManager;
	private SQLiteDatabase myDb;
	private TextView sms_wyslaneTv;
	private TextView sms_odebraneTv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.sms_activity);
    	dataEvent = new DataEvent(this);  
    	dataBaseManager = new DataBaseManager();
	    
	    sms_wyslaneTv = (TextView) findViewById(R.id.sms_wyslaneTv);
	    sms_odebraneTv = (TextView) findViewById(R.id.sms_odebraneTv);
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
    	sumaSmsPrzych = dataBaseManager.SumujDane(dataEvent, myDb, KOLUMNA_2, NAZWA_TABELI_4);
    	sumaSmsWych = dataBaseManager.SumujDane(dataEvent, myDb, KOLUMNA_2, NAZWA_TABELI_5);
    	
    	if(sumaSmsPrzych != null){
    		sms_odebraneTv.setText("Iloœæ odebranych smsów: \n "+ sumaSmsPrzych );
    	}
    	else{
    		sms_odebraneTv.setText("Iloœæ odebranych smsów: \n 0");
    	}
    	
    	if(sumaSmsWych != null){
    		sms_wyslaneTv.setText("Iloœæ wys³anych smsów: \n "+ sumaSmsWych );
    	}
    	else{
    		sms_wyslaneTv.setText("Iloœæ wys³anych smsów: \n 0");
    	}
	}
	}
