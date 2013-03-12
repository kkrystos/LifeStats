package kpm.ls;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class StartServiceAtBootReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Toast.makeText(context, "wystartowa³em z Servisami", Toast.LENGTH_SHORT).show();
		
        context.startService(new Intent(context, PhoneStateService.class));
        context.startService(new Intent(context, SmsService.class)); 
        context.startService(new Intent(context, DefaultService.class));
	}

}
