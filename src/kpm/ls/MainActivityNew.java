package kpm.ls;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivityNew extends Activity {
	ArrayList<String> arrayList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		LinearLayout linearLayout = new LinearLayout(this);
		linearLayout.setOrientation(LinearLayout.VERTICAL);

		LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.FILL_PARENT);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
	            LinearLayout.LayoutParams.MATCH_PARENT,
	            LinearLayout.LayoutParams.WRAP_CONTENT);

	        SharedPreferences getPrefs = PreferenceManager
					.getDefaultSharedPreferences(getApplicationContext());
			boolean isPolaczenia = getPrefs.getBoolean("polaczenia_check", false);
			boolean isSMS = getPrefs.getBoolean("sms_check", false);
			boolean isEmail = getPrefs.getBoolean("email_check", false);
			boolean isKlikniecia = getPrefs.getBoolean("klikniecia_check", false);
			boolean isKroki = getPrefs.getBoolean("kroki_check", false);
			boolean isLadowanie = getPrefs.getBoolean("ladowanie_check", false);
			boolean isAplikacje = getPrefs.getBoolean("aplikacje_check", false);
			boolean isInternet = getPrefs.getBoolean("internet_check", false);
			boolean isPion_Poziom = getPrefs.getBoolean("pion_poziom_check", false);
			boolean isMuzyka = getPrefs.getBoolean("muzyka_check", false);
			boolean isSmartfon = getPrefs.getBoolean("smartfon_check", false);
			
			arrayList = new ArrayList<String>();

			if (isPolaczenia) {
				arrayList.add("polaczenia");
				addButton(getApplicationContext(), linearLayout, lp, "isPolaczenia", PolaczeniaActivity.class);
			}
			if (isSMS) {
				arrayList.add("sms");
				addButton(getApplicationContext(), linearLayout, lp, "isSMS", SMSActivity.class);
			}
			if (isEmail) {
				arrayList.add("email");
				addButton(getApplicationContext(), linearLayout, lp, "isEmail", EMAILActivity.class);
			}
			if (isKlikniecia) {
				arrayList.add("klikniecia");
				addButton(getApplicationContext(), linearLayout, lp, "isKlikniecia", KliknieciaActivity.class);
			}
			if (isKroki) {
				arrayList.add("kroki");
				addButton(getApplicationContext(), linearLayout, lp, "isKroki", SensorClass.class);
			}
			if (isLadowanie) {
				arrayList.add("ladowanie");
				addButton(getApplicationContext(), linearLayout, lp, "isLadowanie", LadowanieActivity.class);
			}
			if (isAplikacje) {
				arrayList.add("aplikacje");
				addButton(getApplicationContext(), linearLayout, lp, "isAplikacje", UruchomieniaActivity.class);
			}
			if (isInternet) {
				arrayList.add("internet");
				addButton(getApplicationContext(), linearLayout, lp, "isInternet", InternetCzasActivity.class);
			}
			if (isPion_Poziom) {
				arrayList.add("pion/poziom");
				addButton(getApplicationContext(), linearLayout, lp, "isPion_Poziom", PionPoziomActivity.class);
			}
			if (isMuzyka) {
				arrayList.add("muzyka");
				addButton(getApplicationContext(), linearLayout, lp, "isMuzyka", MuzykaCzasActivity.class);
			}
			if (isSmartfon) {
				arrayList.add("smartfon");
				addButton(getApplicationContext(), linearLayout, lp, "isSmartfon", SmartfonCzasActivity.class);
			}
			
			setContentView(linearLayout, llp);
		}
	
	public void addButton(final Context context, LinearLayout linearLayout,LinearLayout.LayoutParams lp, final String nazwaBtn, Class<?> cls ){
		 final Class<?> klasa = cls;
		 Button btn = new Button(context);
	     btn.setText(nazwaBtn);	     
	     btn.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
				Intent intent = new Intent(context.getApplicationContext(),klasa);
				intent.putExtra("odchyleniePlusSrednia", 0.0);
				intent.putExtra("arrayList", arrayList);
				startActivity(intent);
//				 Toast.makeText(getApplicationContext(), ""+ arrayList, Toast.LENGTH_SHORT).show();
			}
		});
	        btn.setLayoutParams(lp);
	        linearLayout.addView(btn);
	}

}
