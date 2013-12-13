package com.udb.modulo2.clase35;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.udb.modulo2.clas35.R;

import android.net.ConnectivityManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ToggleButton;

public class MainActivity extends Activity implements OnClickListener {
	
	ToggleButton tglMobileData;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tglMobileData = (ToggleButton) findViewById(R.id.tgl3g);
        tglMobileData.setOnClickListener(this);
        
        TelephonyManager telephonyManager = (TelephonyManager) getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
        if(telephonyManager.getDataState()==TelephonyManager.DATA_CONNECTED){
        	tglMobileData.setChecked(true);
        }
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		TelephonyManager telephonyManager = (TelephonyManager) getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
		switch (telephonyManager.getDataState()) {
        case TelephonyManager.DATA_CONNECTED:
        	setMobileDataEnabled(getApplicationContext(), false);
        	tglMobileData.setChecked(false);
            break;
        case TelephonyManager.DATA_DISCONNECTED:
        	setMobileDataEnabled(getApplicationContext(), true);
        	tglMobileData.setChecked(true);
            break;
        }
	}
    
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void setMobileDataEnabled(Context context, boolean enabled) 
	{
		
	    try{
		   ConnectivityManager conman = (ConnectivityManager)
		   context.getSystemService(Context.CONNECTIVITY_SERVICE);
		   Class conmanClass = Class.forName(conman.getClass().getName());
		   Field iConnectivityManagerField = conmanClass.getDeclaredField("mService");
		   iConnectivityManagerField.setAccessible(true);
		   Object iConnectivityManager = iConnectivityManagerField.get(conman);
		   Class iConnectivityManagerClass = Class.forName(iConnectivityManager.getClass().getName());   
		   Method setMobileDataEnabledMethod = iConnectivityManagerClass.getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
		   setMobileDataEnabledMethod.setAccessible(true);
		   setMobileDataEnabledMethod.invoke(iConnectivityManager, enabled);
	    }catch(Exception e){
	    	
	    }
	}
	

}
