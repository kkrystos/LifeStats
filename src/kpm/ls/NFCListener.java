package kpm.ls;


import android.app.Activity;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.tech.IsoDep;
import android.nfc.tech.MifareClassic;
import android.nfc.tech.MifareUltralight;
import android.nfc.tech.Ndef;
import android.nfc.tech.NfcA;
import android.nfc.tech.NfcB;
import android.nfc.tech.NfcF;
import android.nfc.tech.NfcV;
import android.os.Bundle;
import android.widget.TextView;

public class NFCListener extends Activity{
	
	TextView textView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nfc_listener);
		textView = (TextView) findViewById(R.id.nfcListenerTV1);
		
		
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		byte[] tagId = getIntent().getByteArrayExtra(NfcAdapter.EXTRA_ID);
//		byte[] tag = getIntent().getByteArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
		NdefMessage[] msgs = (NdefMessage[]) getIntent().getParcelableArrayExtra(NfcAdapter.EXTRA_TAG);
		
	    String[] techList =  new String[] { NfcA.class.getName(),
	            NfcB.class.getName(), NfcF.class.getName(),
	            NfcV.class.getName(), IsoDep.class.getName(),
	            MifareClassic.class.getName(),
	            MifareUltralight.class.getName(), Ndef.class.getName()  };
	    StringBuffer sb = new StringBuffer();
	    for(String s : techList){
	    	
	    	sb.append(s);
	    	sb.append("\n");
	    }
	    
//		IntentFilter intentFilter = this.getIntent().getAction(); 
	    
		textView.setText(""+ByteArrayToHexString(tagId)+"\n"+getIntent().getAction().toString()+ "\n");

//		Toast.makeText(getApplicationContext(), "NFC SUKO!!! xDDD"+ByteArrayToHexString(tagId), Toast.LENGTH_SHORT).show();
//		finish();
	}
	String ByteArrayToHexString(byte [] inarray) 
    {
    int i, j, in;
    String [] hex = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
    String out= "";

    for(j = 0 ; j < inarray.length ; ++j) 
        {
        in = (int) inarray[j] & 0xff;
        i = (in >> 4) & 0x0f;
        out += hex[i];
        i = in & 0x0f;
        out += hex[i];
        }
    return out;
}
}
