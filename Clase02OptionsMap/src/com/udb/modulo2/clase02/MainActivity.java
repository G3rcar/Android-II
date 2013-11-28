package com.udb.modulo2.clase02;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.udb.modulo2.clase02.beans.MapOptionsBean;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity implements DialogMapOptions.OptionsMapDialogListener {
	
	private GoogleMap mMap;
	private UiSettings mUiSettings;
	private MapOptionsBean optionsbean;
	public final static String MAP_OPTIONS = "com.udb.modulo2.clase01.MAPOPTIONS";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		setUpMap();
		
		
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		setUpMap();
	}
	
	private void setUpMap() {
        if (mMap == null) {
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        }
        
        if(mMap!=null){
        	//mMap.setMyLocationEnabled(true);
        	mUiSettings = mMap.getUiSettings();
        }
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
        case R.id.action_settings:
        	DialogFragment mapOptions = new DialogMapOptions();
        	Bundle b = new Bundle();
        	b.putSerializable(MAP_OPTIONS, optionsbean);
        	mapOptions.setArguments(b);
        	mapOptions.show(getSupportFragmentManager(), "Opciones");
        	break;
        }
        return true;
    }
	
	@Override
	public void onDialogPositiveClick(DialogFragment dialog, MapOptionsBean optionsbean) {
		// TODO Auto-generated method stub
		if(optionsbean.getMaptype().equals("Normal")){
			mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		}else if(optionsbean.getMaptype().equals("Satelite")){
			mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
		}else if(optionsbean.getMaptype().equals("Terreno")){
			mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
		}else if(optionsbean.getMaptype().equals("Hibrido")){
			mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
		}
		
		mUiSettings.setZoomControlsEnabled(optionsbean.isZoomcontroll());
		mUiSettings.setRotateGesturesEnabled(optionsbean.isRotategesture());
		mUiSettings.setScrollGesturesEnabled(optionsbean.isScrollgesture());
		mUiSettings.setZoomGesturesEnabled(optionsbean.isZoomgesture());
		mMap.setMyLocationEnabled(optionsbean.isMyposition());
		
	}

}
