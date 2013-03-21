package kpm.ls;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SensorClass extends Activity implements SensorEventListener,
		OnClickListener {
	private SensorManager mgr;
	private Sensor accelerometer;
	private TextView text;
	private TextView text2;
	private TextView text3;
	private int mRotation;
	private int steps = 0;
	private double odchyleniePlusSrednia = 0.0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sensor_layout);

        Bundle extras = getIntent().getExtras();
        odchyleniePlusSrednia = extras.getDouble("odchyleniePlusSrednia");
//        if(odchyleniePlusSrednia == 0.0){
//        	Toast.makeText(getApplicationContext(), "Naucz mnie chodziæ :-)", Toast.LENGTH_SHORT).show();
//        }
		
		mgr = (SensorManager) this.getSystemService(SENSOR_SERVICE);

		accelerometer = mgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

		Button nauczMnieBtn = (Button) findViewById(R.id.sensor_nauczBtn);
		nauczMnieBtn.setOnClickListener(this);

//		text = (TextView) findViewById(R.id.text);
//		text2 = (TextView) findViewById(R.id.text2);
		text3 = (TextView) findViewById(R.id.text3);

		WindowManager window = (WindowManager) this
				.getSystemService(WINDOW_SERVICE);
		int apiLevel = Integer.parseInt(Build.VERSION.SDK);

		if (apiLevel < 8) {
			mRotation = window.getDefaultDisplay().getOrientation();
		} else {
			mRotation = window.getDefaultDisplay().getRotation();
		}
	}

	@Override
	protected void onResume() {
		if(odchyleniePlusSrednia != 0.0){
			mgr.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);
		}
		
		super.onResume();
	}

	@Override
	protected void onPause() {
		mgr.unregisterListener(this, accelerometer);
		super.onPause();
	}

	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// ignoruje
	}

	public void onSensorChanged(SensorEvent event) {

//		String msgg = String.format("X: %8.4f\nY: %8.4f\nZ: %8.4f\nObrót: %d",
//				event.values[0], event.values[1], event.values[2], mRotation);

		float x = event.values[0];
		float y = event.values[1];
		float z = event.values[2];
		float g = (x * x + y * y + z * z)
				/ (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);
		String msg = "" + g;
//		if (g > 1.4 && g < 1.5) {
//			steps = steps + 1;
//		}
		if (g > odchyleniePlusSrednia) {
		steps = steps + 1;
	}


		Log.i("", msg);
//		text.setText(msgg);
//		text.invalidate();
		text3.setText("Kroki: " + steps);
//		text2.setText(msg);
//		text2.invalidate();
		text3.invalidate();
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.sensor_nauczBtn:
			Intent i = new Intent(this, StepsLearnActivity.class);
			startActivity(i);
			finish();
		}

	}
}