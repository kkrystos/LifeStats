package kpm.ls;

import static kpm.ls.db.Const.KOLUMNA_2;
import static kpm.ls.db.Const.NAZWA_TABELI_4;
import static kpm.ls.db.Const.NAZWA_TABELI_5;

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
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SMSActivity extends Activity{
	
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    private GestureDetector gestureDetector;
    ArrayList<String> arrayList;
	
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
	    
	    Button button_menu = (Button) findViewById(R.id.button_menu);
	    button_menu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SMSActivity.this, MainActivity.class);
				startActivity(intent);
				finish();
				
			}
		});
	    
	    // anim
	    
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
        
        Bundle bundle = getIntent().getExtras();
        arrayList = bundle.getStringArrayList("arrayList");
        Toast.makeText(getApplicationContext(), ""+ arrayList, Toast.LENGTH_SHORT).show();
       
        
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
	class MyGestureDetector extends SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
	    Intent intentL = new Intent(SMSActivity.this.getBaseContext(), PolaczeniaActivity.class);
	    intentL.putExtra("arrayList", arrayList);
	    Intent intentR = new Intent(SMSActivity.this.getBaseContext(), EMAILActivity.class);
	    intentR.putExtra("arrayList", arrayList);
 
            if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH) {
                return false;
            }
 
            // right to left swipe
            if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
    		startActivity(intentR);
    		SMSActivity.this.overridePendingTransition(
			R.anim.slide_in_right,
			R.anim.slide_out_left
    		);
    	    // right to left swipe
    		finish();
            }  else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
    		startActivity(intentL);
    		SMSActivity.this.overridePendingTransition(
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
