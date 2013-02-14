package kpm.ls;

import static kpm.ls.db.Const.KOLUMNA_2;
import static kpm.ls.db.Const.NAZWA_TABELI_3;
import kpm.ls.db.DataEvent;
import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;

public class KliknieciaActivity extends Activity{
	private String sumaKlikniec ="0";
	private DataEvent dataEvent;
	private DataBaseManager dataBaseManager;
	private SQLiteDatabase myDb;
	private int count = 0;
	private TextView dotknieciaTxt;
	private Vibrator wibracja;
	
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
    setContentView(R.layout.klikniecia_activity);
    
    
    dotknieciaTxt = (TextView) findViewById(R.id.dotknieciaTxt);
    wibracja = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE) ;
}
@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
    	dataEvent = new DataEvent(this);  
    	dataBaseManager = new DataBaseManager();
    	sumaKlikniec = dataBaseManager.SumujDane(dataEvent, myDb, KOLUMNA_2, NAZWA_TABELI_3);
    	count = Integer.parseInt(sumaKlikniec);
    	
    	if(sumaKlikniec != null){
    		dotknieciaTxt.setText("Dotkniêto mnie: \n"+ sumaKlikniec +" razy :-)");
    		count = Integer.parseInt(sumaKlikniec);
    	}
	}
@Override
public boolean onTouchEvent(MotionEvent event) {
	// TODO Auto-generated method stub
	int action = event.getAction();
	switch (action & MotionEvent.ACTION_MASK) {
	case MotionEvent.ACTION_POINTER_DOWN:
		count = count +1;
		dotknieciaTxt.setText("Dotkniêto mnie: \n"+ count +" razy :-)");
//		dataBaseManager.dodajZdarzenie(dataEvent, NAZWA_TABELI_3, "", "1", "", "", "");
		dataBaseManager.Update(dataEvent, myDb, KOLUMNA_2, NAZWA_TABELI_3, count);
		wibracja.vibrate(50);
		break;
	case MotionEvent.ACTION_DOWN:
		count = count +1;
		dotknieciaTxt.setText("Dotkniêto mnie: \n"+ count +" razy :-)");
//		dataBaseManager.dodajZdarzenie(dataEvent, NAZWA_TABELI_3, "", "1", "", "", "");
		dataBaseManager.Update(dataEvent, myDb, KOLUMNA_2, NAZWA_TABELI_3, count);
		wibracja.vibrate(50);
		break;
	case MotionEvent.ACTION_OUTSIDE:
		wibracja.vibrate(50);
		Toast.makeText(getApplicationContext(), "TAP", Toast.LENGTH_SHORT).show();
		break;
	}
	return super.onTouchEvent(event);
}
}
