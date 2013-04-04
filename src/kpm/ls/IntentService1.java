package kpm.ls;

import static kpm.ls.db.Const.NAZWA_TABELI_10;
import static kpm.ls.db.Const.NAZWA_TABELI_12;
import static kpm.ls.db.Const.NAZWA_TABELI_14;

import java.io.IOException;

import kpm.ls.db.DataEvent;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.ActivityManager;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class IntentService1 extends IntentService {

	private int czasChrome = 0;
	private int czasBrowser = 0;
	static boolean check = false;
	private DataEvent dataEvent;
	private DataBaseManager dataBaseManager;
	private static final String ACCOUNT_TYPE_GOOGLE = "com.google";
	private static final String[] FEATURES_MAIL = { "service_mail" };
	static Uri BASE_URI_STRING = Uri.parse("content://com.google.android.gm/");
	
	private int inboxFirst = 0;
	private int outboxFirst = 0;

	public IntentService1() {
		super("IntentService1");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		dataEvent = new DataEvent(getApplicationContext());
		dataBaseManager = new DataBaseManager();
		Log.i("TAGus", "IntentService1 uruchomiony ");

		if (check == false) {
			SimpleThread simpleThread = new SimpleThread("Browsers time Count");
			MusicPlayerThread musicPlayerThread = new MusicPlayerThread(
					"MusicPlayerThread");
			DefaultThread defaultThread = new DefaultThread("DefaultThread");
			simpleThread.start();
			musicPlayerThread.start();
			defaultThread.start();
			check = true;
		}
	}

	class SimpleThread extends Thread {
		int i = 0;

		public SimpleThread(String str) {
			super(str);
		}

		public void run() {
			while (true) {
				i++;
				Log.i("TAGus", i + " " + getName());
				ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
				String packageName = am.getRunningTasks(1).get(0).topActivity
						.getPackageName();
				// String className =
				// am.getRunningTasks(1).get(0).topActivity.getClassName();
				if (packageName.contains("chrome")) {
					czasChrome = czasChrome + 3;
					Log.i("TAGus", "pack: " + packageName);
					Log.i("TAGus", "czas: " + czasChrome);
					dataBaseManager.dodajZdarzenie(dataEvent, NAZWA_TABELI_10,
							"czasChrome", "" + 3, "czasBrowser", "" + 0, "");
				} else if (packageName.contains("com.android.browser")) {
					czasBrowser = czasBrowser + 3;
					Log.i("TAGus", "pack: " + packageName);
					Log.i("TAGus", "czas: " + czasBrowser);
					dataBaseManager.dodajZdarzenie(dataEvent, NAZWA_TABELI_10,
							"czasChrome", "" + 0, "czasBrowser", "" + 3, "");
				}
				try {
					sleep(3000);
				} catch (InterruptedException e) {
				}
			}
		}
	}

	class MusicPlayerThread extends Thread {
		int i = 0;

		public MusicPlayerThread(String str) {
			super(str);
		}

		public void run() {
			while (true) {
				i++;
				Log.i("TAGus", i + " " + getName());
				AudioManager manager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
				if (manager.isMusicActive()) {
					Log.i("TAGus", "Muza gra!!!!");
					dataBaseManager.dodajZdarzenie(dataEvent, NAZWA_TABELI_12,
							"muzyka", "" + 3, "", "", "");
				} else {
					Log.i("TAGus", "Muza NIEE gra!!!!");
				}
				try {
					sleep(3000);
				} catch (InterruptedException e) {
				}
			}
		}
	}

	class DefaultThread extends Thread {
		int i = 0;

		public DefaultThread(String str) {
			super(str);
		}

		public void run() {
			while (true) {

				AccountManager.get(getApplicationContext())
						.getAccountsByTypeAndFeatures(ACCOUNT_TYPE_GOOGLE,
								FEATURES_MAIL,
								new AccountManagerCallback<Account[]>() {
									@Override
									public void run(
											AccountManagerFuture<Account[]> future) {
										Account[] accounts = null;
										try {
											accounts = future.getResult();
										} catch (OperationCanceledException oce) {
										} catch (IOException ioe) {
										} catch (AuthenticatorException ae) {
										}
										if (accounts != null
												&& accounts.length > 0) {
											final String account = accounts[0].name;
											final Bundle args = new Bundle();
											args.putString("account", account);

											Cursor c = getContentResolver()
													.query(getLabelsUri(account),
															null, null, null,
															null);
		if (c != null) {
		final String inboxCanonicalName = "^i";
		final String sentCanonicalName = "^f";
		final int canonicalNameIndex = c.getColumnIndexOrThrow("canonicalName");
		while (c.moveToNext()) {
				if (inboxCanonicalName.equals(c.getString(canonicalNameIndex))) {
					int unreadColumn = c.getColumnIndex("numUnreadConversations");
					int nameColumn = c.getColumnIndex("numConversations");
						String read = c.getString(nameColumn);
						String unread = c.getString(unreadColumn);
						int readInt = Integer.parseInt(read);
						if(inboxFirst == 0){
							inboxFirst = Integer.parseInt(read);
						}
						
						int wynik = readInt - inboxFirst;
						if (wynik !=0 ){
						dataBaseManager.dodajZdarzenie(dataEvent, NAZWA_TABELI_14, "inbox", "" + wynik, "sent", "0", "");	
							
						Log.i("TAGus","wynik: "+ wynik);
							wynik = 0;
							inboxFirst = readInt;
						}
						Log.i("TAGus","odebrane \n"+ read+ "\n"+ unread);

						}
				if (sentCanonicalName.equals(c.getString(canonicalNameIndex))) {
					int unreadColumn = c.getColumnIndex("numUnreadConversations");
					int nameColumn = c.getColumnIndex("numConversations");
						String read = c.getString(nameColumn);
						String unread = c.getString(unreadColumn);
						int readInt = Integer.parseInt(read);
						if(outboxFirst == 0){
							outboxFirst = Integer.parseInt(read);
						}
						
						int wynik = readInt - outboxFirst;
						if (wynik !=0 ){
						dataBaseManager.dodajZdarzenie(dataEvent, NAZWA_TABELI_14, "inbox", "0", "sent", ""+ wynik, "");
						Log.i("TAGus","wynik: "+ wynik);
						wynik = 0;
						outboxFirst = readInt;
						}
						Log.i("TAGus","wys³ane \n"+ read+ "\n"+ unread);
						}
	}
		}
		}
		}
	}, null /* handler */);

				try {
					sleep(3000);
				} catch (InterruptedException e) {
				}
			}
		}
	}

	public static Uri getLabelsUri(String account) {
		return Uri.parse(BASE_URI_STRING + "/" + account + "/labels");
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
}
