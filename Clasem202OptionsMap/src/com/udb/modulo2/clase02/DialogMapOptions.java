package com.udb.modulo2.clase02;

import com.udb.modulo2.clase02.beans.MapOptionsBean;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;

public class DialogMapOptions extends DialogFragment {
	MapOptionsBean optionsbean;
	OptionsMapDialogListener mListener;
	CheckBox chkZoomcontrol, chkRotategesture, chkSrollgesture, chkZoomGesture, chkCompass, chkMylocation;
	Spinner spnMapType;
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
	    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    LayoutInflater inflater = getActivity().getLayoutInflater();
	    View view = inflater.inflate(R.layout.dialog_show_options, null);
        
	    
	    optionsbean =  (MapOptionsBean) ((Bundle)getArguments()).getSerializable(MainActivity.MAP_OPTIONS);
	    chkZoomcontrol = (CheckBox) view.findViewById(R.id.chkZoomcontrol);
	    chkZoomcontrol.setChecked(optionsbean.isZoomcontroll());
	   
	    chkRotategesture = (CheckBox) view.findViewById(R.id.chkRotategesture);
	    chkRotategesture.setChecked(optionsbean.isRotategesture());
	    
	    chkSrollgesture = (CheckBox) view.findViewById(R.id.chkScrollgesture);
	    chkSrollgesture.setChecked(optionsbean.isScrollgesture());
	    
	    chkZoomGesture = (CheckBox) view.findViewById(R.id.chkZoomgesture);
	    chkZoomGesture.setChecked(optionsbean.isZoomgesture());
	    
	    //chkMylocation = (CheckBox) view.findViewById(R.id.chkMyLocation);
	    //chkMylocation.setChecked(optionsbean.isMyposition());
	    
	    spnMapType = (Spinner) view.findViewById(R.id.spn_map_type);
	    @SuppressWarnings("unchecked")
		ArrayAdapter<String> adapter = (ArrayAdapter<String>) spnMapType.getAdapter();
	    spnMapType.setSelection(adapter.getPosition(optionsbean.getMaptype()));
	    
	    builder.setTitle(getResources().getText(R.string.lbltitleoptions));
	    builder.setView(view);
	    builder.setPositiveButton(R.string.btnok, new DialogInterface.OnClickListener() {
	               @Override
	               public void onClick(DialogInterface dialog, int id) {
	                   optionsbean.setMaptype((String)spnMapType.getSelectedItem());
	                   optionsbean.setRotategesture(chkRotategesture.isChecked());
	                   optionsbean.setScrollgesture(chkSrollgesture.isChecked());
	                   optionsbean.setZoomcontroll(chkZoomcontrol.isChecked());
	                   optionsbean.setZoomgesture(chkZoomGesture.isChecked());
	                   optionsbean.setMyposition(chkMylocation.isChecked());
	                   mListener.onDialogPositiveClick(DialogMapOptions.this, optionsbean);
	               }
	           })
	           .setNegativeButton(R.string.btncancel, new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int id) {
	                   
	               }
	           });  
	    return builder.create();
	}
	
	@Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OptionsMapDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()+ " must implement OptionsMapDialogListener");
        }
    }
	
	public interface OptionsMapDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog, MapOptionsBean optionsbean);
    }
}
