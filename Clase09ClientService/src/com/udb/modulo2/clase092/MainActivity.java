package com.udb.modulo2.clase092;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends Activity {
	
	private static final String TAG = "Client";
	private IQuoteService quoteService = null;
	private ToggleButton bindBtn;
	private Button callBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		bindBtn = (ToggleButton) findViewById(R.id.bindBtn);
		callBtn = (Button) findViewById(R.id.callBtn);
		callBtn.setEnabled(false);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void doClick(View view) {
		switch (view.getId()) {
		case R.id.bindBtn:
			if (((ToggleButton) view).isChecked()) {
				bindService(new Intent(IQuoteService.class.getName()),serConn, Context.BIND_AUTO_CREATE);
				callBtn.setEnabled(true);
			} else {
				unbindService(serConn);
				callBtn.setEnabled(false);
			}
			break;
		case R.id.callBtn:
			callService();
			break;
		}
	}

	private void callService() {
		try {
			double val = quoteService.getQuote("ANDROID");
			Toast.makeText(MainActivity.this, "Valor desde el servicio: " + val,Toast.LENGTH_SHORT).show();
		} catch (RemoteException ee) {
			Log.e("MainActivity", ee.getMessage(), ee);
		}
	}

	private ServiceConnection serConn = new ServiceConnection() {
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			Log.d(TAG, "onServiceConnected()");
			quoteService = IQuoteService.Stub.asInterface(service);
			bindBtn.setChecked(true);
			callBtn.setEnabled(true);
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			Log.d(TAG, "onServiceDisconnected()");
			bindBtn.setChecked(false);
			callBtn.setEnabled(false);
			quoteService = null;
		}
	};

	protected void onDestroy() {
		Log.d(TAG, "onDestroy()");
		if (callBtn.isEnabled())
			unbindService(serConn);
		super.onDestroy();
	}

}
