package kpm.ls;

import static kpm.ls.db.Const.KOLUMNA_2;
import static kpm.ls.db.Const.KOLUMNA_4;
import static kpm.ls.db.Const.NAZWA_TABELI_14;

import java.util.ArrayList;

import kpm.ls.SMSActivity.MyGestureDetector;
import kpm.ls.db.DataEvent;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.widget.TextView;
import android.widget.Toast;

public class EMAILActivity extends Activity {
	 private static final int SWIPE_MIN_DISTANCE = 120;
	    private static final int SWIPE_MAX_OFF_PATH = 250;
	    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
	    private GestureDetector gestureDetector;
	    ArrayList<String> arrayList;

	private TextView emailsINTv;
	private TextView emailsOUTTv;
	private DataEvent dataEvent;
	private DataBaseManager dataBaseManager;
	private SQLiteDatabase myDb;

	private String emailsIN = "0";
	private String emailsOUT = "0";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.email_activity);

		dataEvent = new DataEvent(this);
		dataBaseManager = new DataBaseManager();

		emailsOUTTv = (TextView) findViewById(R.id.emailOUTTv);
		emailsINTv = (TextView) findViewById(R.id.emailINTv);
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

		emailsOUT = dataBaseManager.SumujDane(dataEvent, myDb, KOLUMNA_4,
				NAZWA_TABELI_14);
		emailsIN = dataBaseManager.SumujDane(dataEvent, myDb, KOLUMNA_2,
				NAZWA_TABELI_14);

		if (emailsOUT != null) {

			emailsOUTTv.setText("Wys³a³eœ : " + emailsOUT);
		}

		if (emailsIN != null) {

			emailsINTv.setText("Odebra³eœ : " + emailsIN);
		}

	}
	class MyGestureDetector extends SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
	    Intent intentL = new Intent(EMAILActivity.this.getBaseContext(), SMSActivity.class);
	    intentL.putExtra("arrayList", arrayList);
	    Intent intentR = new Intent(EMAILActivity.this.getBaseContext(), PolaczeniaActivity.class);
	    intentR.putExtra("arrayList", arrayList);
 
            if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH) {
                return false;
            }
 
            // right to left swipe
            if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
    		startActivity(intentR);
    		EMAILActivity.this.overridePendingTransition(
			R.anim.slide_in_right,
			R.anim.slide_out_left
    		);
    	    // right to left swipe
    		finish();
            }  else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
    		startActivity(intentL);
    		EMAILActivity.this.overridePendingTransition(
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

