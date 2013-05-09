package kpm.ls;



import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentFilter.MalformedMimeTypeException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.nfc.tech.IsoDep;
import android.nfc.tech.MifareClassic;
import android.nfc.tech.MifareUltralight;
import android.nfc.tech.Ndef;
import android.nfc.tech.NfcA;
import android.nfc.tech.NfcB;
import android.nfc.tech.NfcF;
import android.nfc.tech.NfcV;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;

public class NFCActivity extends Activity{
	
	PendingIntent pendingIntent;
	IntentFilter[] intentFiltersArray;
	String[][] techList;
	NfcAdapter nfcAdapter;
	
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
		
		/// stack http://stackoverflow.com/questions/5685946/nfc-broadcastreceiver-problem
		
		pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,
                getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
//		
		IntentFilter ndef = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
	    try {
	        ndef.addDataType("*/*");
	    } catch (MalformedMimeTypeException e) {
	        throw new RuntimeException("fail", e);
	    }

	    IntentFilter tech = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);
	    try {
	        tech.addDataType("*/*");
	    } catch (MalformedMimeTypeException e) {
	        throw new RuntimeException("fail", e);
	    }
	    IntentFilter tag = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);
	    try {
	        tech.addDataType("*/*");
	    } catch (MalformedMimeTypeException e) {
	        throw new RuntimeException("fail", e);
	    }

	    intentFiltersArray = new IntentFilter[] { tag, ndef, tech };
//		
	    techList = new String[][] { new String[] { NfcA.class.getName(),
	            NfcB.class.getName(), NfcF.class.getName(),
	            NfcV.class.getName(), IsoDep.class.getName(),
	            MifareClassic.class.getName(),
	            MifareUltralight.class.getName(), Ndef.class.getName() } };
	    
	    nfcAdapter = NfcAdapter.getDefaultAdapter(this);
		nfcAdapter.enableForegroundDispatch(this, pendingIntent, intentFiltersArray, techList);

	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		
	}
	@Override
	protected void onPause() {
	    super.onPause();
	    nfcAdapter.disableForegroundDispatch(this);
	}
	
	@Override
	public void onNewIntent(Intent intent) {
	    String action = intent.getAction();

	    if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)) {
	        // reag TagTechnology object...
	    	Toast.makeText(getApplicationContext(), "ACTION_TAG_DISCOVERED", Toast.LENGTH_SHORT).show();
	    } else if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {
	        // read NDEF message...
	    	Toast.makeText(getApplicationContext(), "ACTION_NDEF_DISCOVERED", Toast.LENGTH_SHORT).show();
	    } else if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)) {
	    	Toast.makeText(getApplicationContext(), "ACTION_TECH_DISCOVERED", Toast.LENGTH_SHORT).show();

	    }
	}
	
	
	
	
//	@Override
//	protected void onNewIntent(Intent intent) {
//		// TODO Auto-generated method stub
//		super.onNewIntent(intent);
//		 String action = intent.getAction();
//
//		 if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)) {
//			 Toast.makeText(getApplicationContext(), "ACTION_TAG_DISCOVERED", Toast.LENGTH_SHORT).show();
//	            // When a tag is discovered we send it to the service to be save. We
//	            // include a PendingIntent for the service to call back onto. This
//	            // will cause this activity to be restarted with onNewIntent(). At
//	            // that time we read it from the database and view it.
//	            Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
//	            NdefMessage[] msgs;
//	            if (rawMsgs != null) {
//	                msgs = new NdefMessage[rawMsgs.length];
//	                for (int i = 0; i < rawMsgs.length; i++) {
//	                    msgs[i] = (NdefMessage) rawMsgs[i];
//	                }
//	            } else {
//	            	 Toast.makeText(getApplicationContext(), "NFC", Toast.LENGTH_SHORT).show();
//	                // Unknown tag type
//	                byte[] empty = new byte[] {};
//	                NdefRecord record = new NdefRecord(NdefRecord.TNF_UNKNOWN, empty, empty, empty);
//	                NdefMessage msg = new NdefMessage(new NdefRecord[] {record});
//	                msgs = new NdefMessage[] {msg};
//	            }
//	            // Setup the views
////	            setTitle(R.string.title_scanned_tag);
////	            buildTagViews(msgs);
//	        } else {
////	            Log.e(TAG, "Unknown intent " + intent);
//	            finish();
//	            return;
//	        }
//	}
}
