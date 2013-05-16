package kpm.ls;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class NFCListener1 extends Activity{
	
	TextView mText;
	NfcAdapter mAdapter;
	PendingIntent mPendingIntent;
	IntentFilter mFilters[];
	String mTechLists[][];


	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	}

	@Override
	public void onStart(){
	    super.onStart();
	    
	    final Intent intent  = getIntent();
	    Intent intentt = new Intent(intent.getAction());
	    
	    getPackageManager().setComponentEnabledSetting(
	    		  new ComponentName("kpm.ls", "kpm.ls.NFCListener"),
	    		 PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
	    		  PackageManager.DONT_KILL_APP);
	    
	    Toast.makeText(getApplicationContext(), "+ DB", Toast.LENGTH_SHORT).show();
	    
	    startActivity(intentt);

	    getPackageManager().setComponentEnabledSetting(
	    		  new ComponentName("kpm.ls", "kpm.ls.NFCListener"),
	    		 PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
	    		  PackageManager.DONT_KILL_APP);

	    finish();
        
	    
	}
	

	}
