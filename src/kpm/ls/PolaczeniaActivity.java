package kpm.ls;

import static kpm.ls.db.Const.KOLUMNA_2;
import static kpm.ls.db.Const.NAZWA_TABELI;
import static kpm.ls.db.Const.NAZWA_TABELI_2;

import java.util.ArrayList;

import kpm.ls.db.DataEvent;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class PolaczeniaActivity extends Activity{
	private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    private GestureDetector gestureDetector;
    
    ArrayList<String> arrayList;
    
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
        
       Bundle bundle = getIntent().getExtras();
       arrayList = bundle.getStringArrayList("arrayList");
       Toast.makeText(getApplicationContext(), ""+ arrayList, Toast.LENGTH_SHORT).show();
//       for(int i = 0; i<arrayList.size(); i++){
//    	   Toast.makeText(getApplicationContext(), arrayList.get(i), Toast.LENGTH_SHORT).show();
//       };
       
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
    	gestureDetector = new GestureDetector(new MyGestureDetector());
        View mainview = (View) findViewById(R.id.mainView);
        mainview.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (gestureDetector.onTouchEvent(event)) {
                    return true;
                }
                return false;
            }
        });
		
	}
	class MyGestureDetector extends SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
	    Intent intentL = new Intent(PolaczeniaActivity.this.getBaseContext(), EMAILActivity.class);
	    intentL.putExtra("arrayList", arrayList);
	    Intent intentR = new Intent(PolaczeniaActivity.this.getBaseContext(), SMSActivity.class);
	    intentR.putExtra("arrayList", arrayList);
 
            if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH) {
                return false;
            }
 
            // right to left swipe
            if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
    		startActivity(intentR);
    		PolaczeniaActivity.this.overridePendingTransition(
			R.anim.slide_in_right,
			R.anim.slide_out_left
    		);
    	    // right to left swipe
    		finish();
            }  else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
    		startActivity(intentL);
    		PolaczeniaActivity.this.overridePendingTransition(
			R.anim.slide_in_left, 
			R.anim.slide_out_right
    		);
    		finish();
            }
 
            return false;
        }
 
        // It is necessary to return true from onDown for the onFling event to register
        @Override
        public boolean onDown(MotionEvent e) {
	        	return true;
        }
    }
	}
