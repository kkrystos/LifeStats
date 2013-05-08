package kpm.ls;
import static kpm.ls.db.Const.NAZWA_TABELI_7;

import java.io.File;

import kpm.ls.db.DataEvent;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Vibrator;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
	EditText editTextCallNr;
	TextView tvCallCount;
	TextView wychodzace_pol_Tv;
	TextView przychodzace_pol_Tv;
	TextView dotknijTxt;
	TextView dotknijTxt2;
	String lastCallnumber;
	private DataEvent dataEvent;
	private DataBaseManager dataBaseManager;
    Vibrator wibracja;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
	    dataEvent = new DataEvent(getApplicationContext());
	    dataBaseManager = new DataBaseManager();
	    dataBaseManager.dodajZdarzenie(dataEvent, NAZWA_TABELI_7, "", "1", "", "", "");
        
//        Button call = (Button)findViewById(R.id.button1);
//        call.setOnClickListener(this);
//        Button start_services = (Button)findViewById(R.id.button3);
//        start_services.setOnClickListener(this);
        Button activityPolaczenia = (Button)findViewById(R.id.polaczenia_mainBtn);
        activityPolaczenia.setOnClickListener(this);
        
        Button activitySms = (Button)findViewById(R.id.sms_mainBtn);
        activitySms.setOnClickListener(this);
        Button activityClick = (Button)findViewById(R.id.klikniecia_mainBtn);
        activityClick.setOnClickListener(this);
        Button activitySensor = (Button)findViewById(R.id.button2);
        activitySensor.setOnClickListener(this);
        Button activityLadowanie = (Button)findViewById(R.id.ladowanie_mainBtn);
        activityLadowanie.setOnClickListener(this);
        Button activityUruchomienia = (Button)findViewById(R.id.uruchomienia_app_mainBtn);
        activityUruchomienia.setOnClickListener(this);
        Button internetCzas = (Button)findViewById(R.id.internet_czasBtn);
        internetCzas.setOnClickListener(this);
        Button pionPoziom = (Button)findViewById(R.id.pionPoziomBtn);
        pionPoziom.setOnClickListener(this);
        Button muzyka = (Button)findViewById(R.id.muzykaBtn);
        muzyka.setOnClickListener(this);
        Button smartfon = (Button)findViewById(R.id.smartfonBtn);
        smartfon.setOnClickListener(this);
        Button email = (Button)findViewById(R.id.email_mainBtn);
        email.setOnClickListener(this);
        Button nfc = (Button)findViewById(R.id.nfcBtn);
        nfc.setOnClickListener(this);
        

//        File dir = new File(Environment.getExternalStorageDirectory()
//                + "/DCIM/Camera");
//        File[] files = dir.listFiles();
//        int numberOfImages=files.length;
//        Toast.makeText(getApplicationContext(), ""+numberOfImages, Toast.LENGTH_SHORT).show();
        
    }
    
    @Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();
    	
    	
    	
    	
        startService(new Intent(this, PhoneStateService.class));
        startService(new Intent(this, SmsService.class)); 
        Intent defaultIntent = new Intent(this, DefaultService.class);
//        defaultIntent.putExtra("account", "kkrystos@gmciaks.com");
        startService(defaultIntent);
    }


	public void onClick(View arg0) {
		switch(arg0.getId()){
//		case R.id.button1:
//			 stopService(new Intent(this, PhoneStateService.class));
////			 stopService(new Intent(this, SmsService.class));
////			 stopService(new Intent(this, DefaultService.class));
//			 stopService(new Intent(this, IntentService1.class));
//			break;
//		case R.id.button3:
//	        startService(new Intent(this, PhoneStateService.class));
//	        startService(new Intent(this, SmsService.class)); 
//	        startService(new Intent(this, DefaultService.class));
//	        startService(new Intent(this, IntentService1.class)); 
//	        break;
		case R.id.polaczenia_mainBtn:
			Intent intPol = new Intent(this,PolaczeniaActivity.class);
			startActivity(intPol);
			break;
		case R.id.sms_mainBtn:
			Intent smsInt = new Intent(this,SMSActivity.class);
			startActivity(smsInt);
			break;
		case R.id.email_mainBtn:
			Intent emailInt = new Intent(this,EMAILActivity.class);
			startActivity(emailInt);
			break;
		case R.id.klikniecia_mainBtn:
			Intent klikInt = new Intent(this,KliknieciaActivity.class);
			startActivity(klikInt);
	        break;
		case R.id.button2:
			Intent i = new Intent(this,SensorClass.class);
			i.putExtra("odchyleniePlusSrednia", 0.0);
			startActivity(i);
	        break;
		case R.id.ladowanie_mainBtn:
			Intent ladInt = new Intent(this,LadowanieActivity.class);
			startActivity(ladInt);
	        break;
		case R.id.uruchomienia_app_mainBtn:
			Intent uruchInt = new Intent(this,UruchomieniaActivity.class);
			startActivity(uruchInt);
	        break;
		case R.id.internet_czasBtn:
			Intent internetInt = new Intent(this,InternetCzasActivity.class);
			startActivity(internetInt);
	        break;
		case R.id.pionPoziomBtn:
			Intent pionPoziomInt = new Intent(this,PionPoziomActivity.class);
			startActivity(pionPoziomInt);
	        break;
		case R.id.muzykaBtn:
			Intent muzykaInt = new Intent(this,MuzykaCzasActivity.class);
			startActivity(muzykaInt);
	        break;
		case R.id.smartfonBtn:
			Intent smartfonInt = new Intent(this,SmartfonCzasActivity.class);
			startActivity(smartfonInt);
	        break;
		case R.id.nfcBtn:
			Intent nfcInt = new Intent(this,NFCActivity.class);
			startActivity(nfcInt);
	        break;
		}
	} 
}
