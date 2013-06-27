package kpm.ls;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class PreferencesActivity extends PreferenceActivity {
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences_activity);
        setContentView(R.layout.main);
 
//        Preference pp = findPreference("editTextPref");
//        pp.setOnPreferenceClickListener(new OnPreferenceClickListener() {
//			public boolean onPreferenceClick(Preference preference) {
//				Intent i = new Intent(getApplicationContext(), MainActivityNew.class);
//				startActivity(i);
////				finish();
//				return false;	
//			}
//		});
        
        Button button = (Button) findViewById(R.id.Button1);
        button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			Intent i = new Intent(getApplicationContext(), MainActivityNew.class);
				startActivity(i);
//				finish();
			}
		});
 
    }

}
