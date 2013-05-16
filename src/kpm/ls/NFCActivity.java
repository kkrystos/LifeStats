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

public class NFCActivity extends Activity{

	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.nfc_activity);
	}   
	}

///// dzia³a 
//public class NFCActivity extends Activity{
//	
//	TextView mText;
//	NfcAdapter mAdapter;
//	PendingIntent mPendingIntent;
//	IntentFilter mFilters[];
//	String mTechLists[][];
//
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//	    super.onCreate(savedInstanceState);
//
//	}
//
//	@Override
//	public void onStart(){
//	    super.onStart();
//	    setContentView(R.layout.nfc_activity);
//
//	    mText = (TextView) findViewById(R.id.nfcTV);
//
//	    mAdapter = NfcAdapter.getDefaultAdapter(this);
//	    mPendingIntent = PendingIntent.getActivity(this, 0,
//	            new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
//
//	    IntentFilter ndef = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
//	    try{
//	        ndef.addDataType("text/plain");
//	    }catch(MalformedMimeTypeException e){
//	        throw new RuntimeException("fail", e);
//	    }
//
//	    IntentFilter nt = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
//	    mFilters = new IntentFilter[]{
//	            ndef, nt
//	    };
//
//	    mTechLists = new String[][]{
//	            new String[]{
//	                    Ndef.class.getName()
//	            }
//	    };
//	    Intent intent = getIntent();
//	    mText.setText(getNdefMessages(intent));
////	    Toast.makeText(getApplicationContext(), ""+getNdefMessages(intent), Toast.LENGTH_LONG).show();
//	}
//
//	public String getNdefMessages(Intent intent){
//	    NdefMessage[] msgs = null;
////	    String string = null ;
//	    String action = intent.getAction();
//	    if(NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)||
//	            NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)){
//	        Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
//	        if(rawMsgs != null){
//	            msgs = new NdefMessage[rawMsgs.length];
//	            for(int i=0; i<rawMsgs.length; i++){
//	                msgs[i] = (NdefMessage) rawMsgs[i];
//	            }
//	        }else{
//	            byte[] empty = new byte[]{};
//	            NdefRecord record = new NdefRecord(NdefRecord.TNF_UNKNOWN, empty, empty, empty);
//	            NdefMessage msg = new NdefMessage(new NdefRecord[]{record});
//	            msgs = new NdefMessage[]{msg};
////	            string = new String(msg.getRecords()[0].getPayload());
////	            msgs[0].getRecords()[0].getPayload();
//	            
//	        }
//
//	    }
//	    if(msgs==null)
//	        return "No Tag discovered!";
//	    else
//	        return msgs[0].getRecords()[0].getPayload().toString();
////	    return string;
//	}
//
//	@Override
//	public void onResume(){
//	    super.onResume();
//	    if (mAdapter != null)
//	        mAdapter.enableForegroundDispatch(this, mPendingIntent, mFilters, mTechLists);
//
//	}
//
//	@Override
//	public void onPause(){
//	    super.onPause();
//	    if (mAdapter != null)
//	        mAdapter.disableForegroundDispatch(this);
//	}
//
//	@Override
//	public void onNewIntent(Intent intent){
//	    Log.i("Foreground dispatch", "Discovered tag with intent:" + intent);
//	    mText = (TextView) findViewById(R.id.nfcTV);
//	    mText.setText(getNdefMessages(intent));
////	    Toast.makeText(getApplicationContext(), ""+getNdefMessages(intent), Toast.LENGTH_LONG).show();
//	}
//
//	}
