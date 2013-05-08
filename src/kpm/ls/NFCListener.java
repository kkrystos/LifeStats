package kpm.ls;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NFCListener extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Toast.makeText(context, "NFC!!!xDDD", Toast.LENGTH_SHORT).show();
		
	}
	
	

}
