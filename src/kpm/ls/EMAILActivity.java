package kpm.ls;

import static kpm.ls.db.Const.KOLUMNA_2;
import static kpm.ls.db.Const.KOLUMNA_4;
import static kpm.ls.db.Const.NAZWA_TABELI_14;
import kpm.ls.db.DataEvent;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class EMAILActivity extends Activity {

	private TextView emailsINTv;
	private TextView emailsOUTTv;
	private DataEvent dataEvent;
	private DataBaseManager dataBaseManager;
	private SQLiteDatabase myDb;

	private String emailsIN = "0";
	private String emailsOUT = "0";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.email_activity);

		dataEvent = new DataEvent(this);
		dataBaseManager = new DataBaseManager();

		emailsOUTTv = (TextView) findViewById(R.id.emailOUTTv);
		emailsINTv = (TextView) findViewById(R.id.emailINTv);

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		emailsOUT = dataBaseManager.SumujDane(dataEvent, myDb, KOLUMNA_4,
				NAZWA_TABELI_14);
		emailsIN = dataBaseManager.SumujDane(dataEvent, myDb, KOLUMNA_2,
				NAZWA_TABELI_14);

		if (emailsOUT != null) {

			emailsOUTTv.setText("Wys³a³eœ : " + emailsOUT);
		}

		if (emailsIN != null) {

			emailsINTv.setText("Odebra³eœ : " + emailsIN);
		}

	}
}
