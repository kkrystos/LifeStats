package kpm.ls;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.os.Bundle;
import android.widget.Toast;

public class NFCActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nfc_activity);
		
		NfcManager manager = (NfcManager)this.getSystemService(Context.NFC_SERVICE);
		NfcAdapter adapter = manager.getDefaultAdapter();
		adapter.isEnabled();
		if(adapter.isEnabled() == false){
			startActivityForResult(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS), 0);
		}
		Toast.makeText(getApplicationContext(), ""+adapter.isEnabled(), Toast.LENGTH_SHORT).show();
	}
	
	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);
		 String action = intent.getAction();

		    if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)) {
		        // reag TagTechnology object...
		    	Toast.makeText(getApplicationContext(), "nfc", Toast.LENGTH_SHORT).show();
		    } else if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {
		        // read NDEF message...
		    	Toast.makeText(getApplicationContext(), "nfc", Toast.LENGTH_SHORT).show();
		    } else if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)) {
		    	Toast.makeText(getApplicationContext(), "nfc", Toast.LENGTH_SHORT).show();
		    }
	}
}
