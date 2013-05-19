package kpm.ls.db;

import static android.provider.BaseColumns._ID;

import static kpm.ls.db.Const.KOLUMNA_1;
import static kpm.ls.db.Const.KOLUMNA_2;
import static kpm.ls.db.Const.KOLUMNA_3;
import static kpm.ls.db.Const.KOLUMNA_4;
import static kpm.ls.db.Const.KOLUMNA_5;
import static kpm.ls.db.Const.NAZWA_TABELI;
import static kpm.ls.db.Const.NAZWA_TABELI_10;
import static kpm.ls.db.Const.NAZWA_TABELI_11;
import static kpm.ls.db.Const.NAZWA_TABELI_12;
import static kpm.ls.db.Const.NAZWA_TABELI_13;
import static kpm.ls.db.Const.NAZWA_TABELI_14;
import static kpm.ls.db.Const.NAZWA_TABELI_15;
import static kpm.ls.db.Const.NAZWA_TABELI_2;
import static kpm.ls.db.Const.NAZWA_TABELI_3;
import static kpm.ls.db.Const.NAZWA_TABELI_4;
import static kpm.ls.db.Const.NAZWA_TABELI_5;
import static kpm.ls.db.Const.NAZWA_TABELI_6;
import static kpm.ls.db.Const.NAZWA_TABELI_7;
import static kpm.ls.db.Const.NAZWA_TABELI_8;
import static kpm.ls.db.Const.NAZWA_TABELI_9;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataEvent extends SQLiteOpenHelper {
	private static final String NAZWA_BAZY_DANYCH = "zdarzenia.db";
	private static final int WERSJA_BAZY_DANYCH = 1;

	/** Tworzy obiekt pomocniczy dla bazy Zdarzenia */
	public DataEvent(Context ktks) {
		super(ktks, NAZWA_BAZY_DANYCH, null, WERSJA_BAZY_DANYCH);
	}

	@Override
	public void onCreate(SQLiteDatabase bd) {
		bd.execSQL("CREATE TABLE " + NAZWA_TABELI + " (" + _ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + KOLUMNA_1 + " TEXT,"
				+ KOLUMNA_2 + " TEXT," + KOLUMNA_3 + " TEXT," + KOLUMNA_4
				+ " TEXT," + KOLUMNA_5 + " TEXT );");
		bd.execSQL("CREATE TABLE " + NAZWA_TABELI_2 + " (" + _ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + KOLUMNA_1 + " TEXT,"
				+ KOLUMNA_2 + " TEXT," + KOLUMNA_3 + " TEXT," + KOLUMNA_4
				+ " TEXT," + KOLUMNA_5 + " TEXT );");
		bd.execSQL("CREATE TABLE " + NAZWA_TABELI_3 + " (" + _ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + KOLUMNA_1 + " TEXT,"
				+ KOLUMNA_2 + " TEXT," + KOLUMNA_3 + " TEXT," + KOLUMNA_4
				+ " TEXT," + KOLUMNA_5 + " TEXT );");
		ContentValues cv = new ContentValues();
		cv.put(KOLUMNA_2, "0");
		bd.insert(NAZWA_TABELI_3, KOLUMNA_2, cv);
		bd.execSQL("CREATE TABLE " + NAZWA_TABELI_4 + " (" + _ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + KOLUMNA_1 + " TEXT,"
				+ KOLUMNA_2 + " TEXT," + KOLUMNA_3 + " TEXT," + KOLUMNA_4
				+ " TEXT," + KOLUMNA_5 + " TEXT );");
		bd.execSQL("CREATE TABLE " + NAZWA_TABELI_5 + " (" + _ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + KOLUMNA_1 + " TEXT,"
				+ KOLUMNA_2 + " TEXT," + KOLUMNA_3 + " TEXT," + KOLUMNA_4
				+ " TEXT," + KOLUMNA_5 + " TEXT );");
		bd.execSQL("CREATE TABLE " + NAZWA_TABELI_6 + " (" + _ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + KOLUMNA_1 + " TEXT,"
				+ KOLUMNA_2 + " TEXT," + KOLUMNA_3 + " TEXT," + KOLUMNA_4
				+ " TEXT," + KOLUMNA_5 + " TEXT );");
		cv.put(KOLUMNA_4, "0");
		bd.insert(NAZWA_TABELI_6, KOLUMNA_4, cv);
		bd.execSQL("CREATE TABLE " + NAZWA_TABELI_7 + " (" + _ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + KOLUMNA_1 + " TEXT,"
				+ KOLUMNA_2 + " TEXT," + KOLUMNA_3 + " TEXT," + KOLUMNA_4
				+ " TEXT," + KOLUMNA_5 + " TEXT );");
		bd.execSQL("CREATE TABLE " + NAZWA_TABELI_8 + " (" + _ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + KOLUMNA_1 + " TEXT,"
				+ KOLUMNA_2 + " TEXT," + KOLUMNA_3 + " TEXT," + KOLUMNA_4
				+ " TEXT," + KOLUMNA_5 + " TEXT );");
		bd.execSQL("CREATE TABLE " + NAZWA_TABELI_9 + " (" + _ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + KOLUMNA_1 + " TEXT,"
				+ KOLUMNA_2 + " TEXT," + KOLUMNA_3 + " TEXT," + KOLUMNA_4
				+ " TEXT," + KOLUMNA_5 + " TEXT );");
		bd.execSQL("CREATE TABLE " + NAZWA_TABELI_10 + " (" + _ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + KOLUMNA_1 + " TEXT,"
				+ KOLUMNA_2 + " TEXT," + KOLUMNA_3 + " TEXT," + KOLUMNA_4
				+ " TEXT," + KOLUMNA_5 + " TEXT );");
		bd.execSQL("CREATE TABLE " + NAZWA_TABELI_11 + " (" + _ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + KOLUMNA_1 + " TEXT,"
				+ KOLUMNA_2 + " TEXT," + KOLUMNA_3 + " TEXT," + KOLUMNA_4
				+ " TEXT," + KOLUMNA_5 + " TEXT );");
		bd.execSQL("CREATE TABLE " + NAZWA_TABELI_12 + " (" + _ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + KOLUMNA_1 + " TEXT,"
				+ KOLUMNA_2 + " TEXT," + KOLUMNA_3 + " TEXT," + KOLUMNA_4
				+ " TEXT," + KOLUMNA_5 + " TEXT );");
		bd.execSQL("CREATE TABLE " + NAZWA_TABELI_13 + " (" + _ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + KOLUMNA_1 + " TEXT,"
				+ KOLUMNA_2 + " TEXT," + KOLUMNA_3 + " TEXT," + KOLUMNA_4
				+ " TEXT," + KOLUMNA_5 + " TEXT );");
		bd.execSQL("CREATE TABLE " + NAZWA_TABELI_14 + " (" + _ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + KOLUMNA_1 + " TEXT,"
				+ KOLUMNA_2 + " TEXT," + KOLUMNA_3 + " TEXT," + KOLUMNA_4
				+ " TEXT," + KOLUMNA_5 + " TEXT );");
		bd.execSQL("CREATE TABLE " + NAZWA_TABELI_15 + " (" + _ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + KOLUMNA_1 + " TEXT,"
				+ KOLUMNA_2 + " TEXT," + KOLUMNA_3 + " TEXT," + KOLUMNA_4
				+ " TEXT," + KOLUMNA_5 + " TEXT );");
		cv.put(KOLUMNA_1, "0");
		cv.put(KOLUMNA_1, "0");
		cv.put(KOLUMNA_3, "0");
		cv.put(KOLUMNA_4, "0");
		cv.put(KOLUMNA_5, "0");
		bd.insert(NAZWA_TABELI_15, KOLUMNA_2, cv);
	}

	@Override
	public void onUpgrade(SQLiteDatabase bd, int staraWersja, int nowaWersja) {
		bd.execSQL("DROP TABLE" + NAZWA_TABELI);
		bd.execSQL("DROP TABLE" + NAZWA_TABELI_2);
		bd.execSQL("DROP TABLE" + NAZWA_TABELI_3);
		bd.execSQL("DROP TABLE" + NAZWA_TABELI_4);
		bd.execSQL("DROP TABLE" + NAZWA_TABELI_5);
		bd.execSQL("DROP TABLE" + NAZWA_TABELI_6);
		bd.execSQL("DROP TABLE" + NAZWA_TABELI_7);
		bd.execSQL("DROP TABLE" + NAZWA_TABELI_8);
		bd.execSQL("DROP TABLE" + NAZWA_TABELI_9);
		bd.execSQL("DROP TABLE" + NAZWA_TABELI_10);
		bd.execSQL("DROP TABLE" + NAZWA_TABELI_11);
		bd.execSQL("DROP TABLE" + NAZWA_TABELI_12);
		bd.execSQL("DROP TABLE" + NAZWA_TABELI_13);
		bd.execSQL("DROP TABLE" + NAZWA_TABELI_14);
		bd.execSQL("DROP TABLE" + NAZWA_TABELI_15);
		onCreate(bd);
	}

	@Override
	public synchronized SQLiteDatabase getWritableDatabase() {
		// TODO Auto-generated method stub
		return super.getWritableDatabase();
	}

}
