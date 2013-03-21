package kpm.ls;

import static kpm.ls.db.Const.NAZWA_TABELI_11;

import java.util.ArrayList;

import kpm.ls.db.DataEvent;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class StepsLearnActivity extends Activity implements
		SensorEventListener, OnClickListener {
	private SensorManager sensorManager;
	private Button btnStart, btnStop, btnUpload;
	private boolean started = false;
	private ArrayList<AccelData> sensorData;
	private ArrayList<Double> sensorDataDouble;
	private LinearLayout layout;
	private View mChart;
	private DataEvent dataEvent;
	private DataBaseManager dataBaseManager;
	private SQLiteDatabase myDb;
	private double gg = 0.0;
	private int count = 0;
	private double odchyleniePlusSrednia = 0.0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.steps_learn_activity);
		layout = (LinearLayout) findViewById(R.id.chart_container);
		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		sensorData = new ArrayList<AccelData>();
		sensorDataDouble = new ArrayList<Double>();

		btnStart = (Button) findViewById(R.id.btnStart);
		btnStop = (Button) findViewById(R.id.btnStop);
		btnUpload = (Button) findViewById(R.id.btnUpload);
		btnStart.setOnClickListener(this);
		btnStop.setOnClickListener(this);
		btnUpload.setOnClickListener(this);
		btnStart.setEnabled(true);
		btnStop.setEnabled(false);
		if (sensorData == null || sensorData.size() == 0) {
			btnUpload.setEnabled(false);
		}
		dataEvent = new DataEvent(getApplicationContext());
		dataBaseManager = new DataBaseManager();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (started == true) {
			sensorManager.unregisterListener(this);
		}
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		if (started) {
			double x = event.values[0];
			double y = event.values[1];
			double z = event.values[2];
			double g = (x * x + y * y + z * z)
					/ (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);
			long timestamp = System.currentTimeMillis();
			AccelData data = new AccelData(timestamp, g);
			sensorData.add(data);
			sensorDataDouble.add(g);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnStart:
			btnStart.setEnabled(false);
			btnStop.setEnabled(true);
			btnUpload.setEnabled(false);
			sensorData = new ArrayList<AccelData>();
			sensorDataDouble = new ArrayList<Double>();
			started = true;
			Sensor accel = sensorManager
					.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
			sensorManager.registerListener(this, accel,
					SensorManager.SENSOR_DELAY_FASTEST);
			break;
		case R.id.btnStop:
			btnStart.setEnabled(true);
			btnStop.setEnabled(false);
			btnUpload.setEnabled(true);
			started = false;
			sensorManager.unregisterListener(this);
			layout.removeAllViews();
			openChart();
			Toast.makeText(
					getApplicationContext(),
					"œrednia= "
							+ calculateAverage(sensorDataDouble)
							+ "\nodchylenie stand.= "
							+ obliczOdchylenie(sensorDataDouble)
							+ "\nœr. + odch.= "
							+ (calculateAverage(sensorDataDouble) + obliczOdchylenie(sensorDataDouble)),
					Toast.LENGTH_SHORT).show();
			odchyleniePlusSrednia = calculateAverage(sensorDataDouble)
					+ obliczOdchylenie(sensorDataDouble);
			break;
		case R.id.btnUpload:
			Intent nauczonyInt = new Intent(this, SensorClass.class);
			nauczonyInt
					.putExtra("odchyleniePlusSrednia", odchyleniePlusSrednia);
			startActivity(nauczonyInt);
			finish();

			break;
		default:
			break;
		}
	}

	private void openChart() {
		if (sensorData != null || sensorData.size() > 0) {
			long t = sensorData.get(0).getTimestamp();
			XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
			XYSeries gSeries = new XYSeries("G");

			for (AccelData data : sensorData) {
				gSeries.add(data.getTimestamp() - t, data.getG());
			}
			dataset.addSeries(gSeries);

			XYSeriesRenderer gRenderer = new XYSeriesRenderer();
			gRenderer.setColor(Color.RED);
			gRenderer.setPointStyle(PointStyle.CIRCLE);
			gRenderer.setFillPoints(true);
			gRenderer.setLineWidth(1);
			gRenderer.setDisplayChartValues(false);

			XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
			multiRenderer.setApplyBackgroundColor(true);
			multiRenderer.setBackgroundColor(Color.BLACK);
			multiRenderer.setXLabels(7);
			multiRenderer.setLabelsColor(Color.RED);
			multiRenderer.setChartTitle("t vs (g)");
			multiRenderer.setXTitle("Sensor Data");
			multiRenderer.setYTitle("Values of Acceleration");
			multiRenderer.setZoomButtonsVisible(true);
			for (int i = 0; i < sensorData.size(); i++) {
				multiRenderer.addXTextLabel(i + 1, ""
						+ (sensorData.get(i).getTimestamp() - t));
			}
			for (int i = 0; i < 12; i++) {
				multiRenderer.addYTextLabel(i + 1, "" + i);
			}

			multiRenderer.addSeriesRenderer(gRenderer);
			mChart = ChartFactory.getLineChartView(getBaseContext(), dataset,
					multiRenderer);
			layout.addView(mChart);
		}
	}

	private double calculateAverage(ArrayList<Double> marks) {
		double sum = 0;
		for (Double mark : marks) {
			sum += mark;
		}
		return sum / marks.size();
	}

	private double obliczOdchylenie(ArrayList<Double> list) {

		double sum = 0.0;
		double srednia = 0.0;
		for (Double mark : list) {
			sum += mark;
		}
		srednia = sum / list.size();

		double odchylenie = 0.0;

		double sumaKwadratow = 0.0;

		for (int i = 0; i < list.size(); i++) {
			sumaKwadratow = sumaKwadratow + (list.get(i) * list.get(i));
		}
		odchylenie = Math.sqrt((sumaKwadratow / list.size())
				- (srednia * srednia));
		return odchylenie;
	}
}

class AccelData {
	private long timestamp;
	private double g;

	public AccelData(long timestamp, double g) {
		this.timestamp = timestamp;
		this.g = g;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public double getG() {
		return g;
	}

	public void setG(double g) {
		this.g = g;
	}

	public String toString() {
		return "t=" + timestamp + ", g=" + g;
	}
}
