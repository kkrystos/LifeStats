package kpm.ls;

import static kpm.ls.db.Const.KOLUMNA_1;
import static kpm.ls.db.Const.KOLUMNA_2;
import static kpm.ls.db.Const.KOLUMNA_3;
import static kpm.ls.db.Const.KOLUMNA_4;
import static kpm.ls.db.Const.KOLUMNA_5;
import kpm.ls.db.DataEvent;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DataBaseManager {
	
    public void dodajZdarzenie(DataEvent dataEvent,String nazwa_tabeli, String kol_1, String kol_2, String kol_3, String kol_4, String kol_5) {
        ContentValues wartosci = new ContentValues();
        wartosci.put(KOLUMNA_1, kol_1);
        wartosci.put(KOLUMNA_2, kol_2);
        wartosci.put(KOLUMNA_3, kol_3);
        wartosci.put(KOLUMNA_4, kol_4);
        wartosci.put(KOLUMNA_5, kol_5);
        dataEvent.getWritableDatabase().insert(nazwa_tabeli, null, wartosci);
        dataEvent.close();
    }
	  public String SumujDane(DataEvent dataEvent,SQLiteDatabase myDb, String kolumna, String tabela){
		  String suma="";
		  myDb = dataEvent.getReadableDatabase();
		  Cursor kursor = myDb.rawQuery("Select SUM ("+ kolumna +") from  "+ tabela + " ", null);
			if(kursor.moveToFirst()) {
			    suma = kursor.getString(0);
			} 
			kursor.close();
			dataEvent.close();
			return suma;	
	  }
	  public void Update(DataEvent dataEvent,SQLiteDatabase myDb, String kolumna, String tabela, int i){
		  ContentValues wartosci = new ContentValues();
		  wartosci.put(KOLUMNA_2,i );
		  myDb = dataEvent.getReadableDatabase();
		  myDb.update(tabela, wartosci, "_id= 1 ", null);
		  dataEvent.close();
	  }
	  
	  public String pobierzOstatnaWartosc(DataEvent dataEvent,SQLiteDatabase myDb, String[] kolumna, String tabela){
		  String ostatniaWartosc = "";
		  myDb = dataEvent.getReadableDatabase();
		  Cursor kursor = myDb.query(tabela, kolumna ,null, null, null, null, null);
		  kursor.moveToLast();
		  ostatniaWartosc = kursor.getString(0);
		  return ostatniaWartosc;
	  }

}
