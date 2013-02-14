package kpm.ls.db;

import android.provider.BaseColumns;

import android.net.Uri;

public interface Const extends BaseColumns {
    public static final String NAZWA_TABELI = "polaczenia_wychodzace" ;
    public static final String NAZWA_TABELI_2 = "polaczenia_przychodzace" ;
    public static final String NAZWA_TABELI_3 = "klikniecia_w_ekran" ;
    public static final String NAZWA_TABELI_4 = "sms_odebrane" ;
    public static final String NAZWA_TABELI_5 = "sms_wyslane" ;
    public static final String NAZWA_TABELI_6 = "ladowanie" ;
    public static final String NAZWA_TABELI_7 = "uruchomienia" ;
    
    public static final String URZAD = "kpm.ls.db" ;
    public static final Uri TRESC_URI = Uri.parse("content://"
        + URZAD + "/" + NAZWA_TABELI);
    public static final Uri TRESC_URI_2 = Uri.parse("content://"
            + URZAD + "/" + NAZWA_TABELI_2);


    // Kolumny w bazie danych Zdarzenia
    public static final String KOLUMNA_1 = "kolumna_1" ;
    public static final String KOLUMNA_2 = "kolumna_2" ;
    public static final String KOLUMNA_3 = "kolumna_3" ;
    public static final String KOLUMNA_4 = "kolumna_4" ;  
    public static final String KOLUMNA_5 = "kolumna_5" ;  
}