package kpm.ls;

import static kpm.ls.db.Const.NAZWA_TABELI_4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import kpm.ls.db.DataEvent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.gsm.SmsMessage;
import android.util.Log;
import android.widget.Toast;
public class SmsListener extends BroadcastReceiver{
	DataEvent dataEvent;
	DataBaseManager dataBaseManager;
	String numer = "";
	String wiadomosc = "";
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		dataEvent = new DataEvent(context);
		dataBaseManager = new DataBaseManager();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
		if(intent!=null && intent.getAction()!=null && "android.provider.Telephony.SMS_Received".compareToIgnoreCase(intent.getAction())==0){
			String currentDateandTime = sdf.format(new Date());
			Toast.makeText(context, "Przyszed³ smsik :-)\n "+currentDateandTime, Toast.LENGTH_SHORT).show();
			Log.i("", "Przyszed³ smsik :-)"+currentDateandTime);
			Object[] pduArray = (Object[])intent.getExtras().get("pdus");
			SmsMessage[] messages = new SmsMessage[pduArray.length];
			for(int i = 0; i<pduArray.length;i++){
				messages[i] = SmsMessage.createFromPdu((byte[])pduArray[i]);
				numer = messages[i].getOriginatingAddress();
				wiadomosc = messages[i].getMessageBody();
			}
			Log.i("", numer);
			Log.i("", wiadomosc);
			dataBaseManager.dodajZdarzenie(dataEvent, NAZWA_TABELI_4, numer, "1", wiadomosc, currentDateandTime, "");
			// nie dla emulatorka kochaniutkiego niestety... :(
//			copyDBtoSD(context);
		}
//		Toast.makeText(context, "Przyszed³ smsik :-)\n ", Toast.LENGTH_SHORT).show();	
	}
	public void copyDBtoSD(Context context){
        File f=new File("/data/data/kpm.ls/databases/zdarzenia.db");
        FileInputStream fis=null;
        FileOutputStream fos=null;

        try
        {
          fis=new FileInputStream(f);
          fos=new FileOutputStream("/mnt/sdcard/zdarzenia.db");
          while(true)
          {
            int i=fis.read();
            if(i!=-1)
            {fos.write(i);}
            else
            {break;}
          }
          fos.flush();
          Toast.makeText(context, "DB dump OK", Toast.LENGTH_LONG).show();
        }
        catch(Exception e)
        {
          e.printStackTrace();
          Toast.makeText(context, "DB dump ERROR", Toast.LENGTH_LONG).show();
        }
        finally
        {
          try
          {
            fos.close();
            fis.close();
          }
          catch(IOException ioe)
          {}
        }   
	}
}
