package com.udb.modulo2.clase02.beans;

import java.io.Serializable;

public class MapOptionsBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String maptype = "Normal";
	private boolean rotategesture = true;
	private boolean scrollgesture = true;
	private boolean zoomcontroll = true;
	private boolean zoomgesture = true;
	private boolean myposition = true;
	
	public boolean isMyposition() {
		return myposition;
	}
	public void setMyposition(boolean myposition) {
		this.myposition = myposition;
	}
	public String getMaptype() {
		return maptype;
	}
	public void setMaptype(String maptype) {
		this.maptype = maptype;
	}
	public boolean isRotategesture() {
		return rotategesture;
	}
	public void setRotategesture(boolean rotategesture) {
		this.rotategesture = rotategesture;
	}
	public boolean isScrollgesture() {
		return scrollgesture;
	}
	public void setScrollgesture(boolean scrollgesture) {
		this.scrollgesture = scrollgesture;
	}
	public boolean isZoomcontroll() {
		return zoomcontroll;
	}
	public void setZoomcontroll(boolean zoomcontroll) {
		this.zoomcontroll = zoomcontroll;
	}
	public boolean isZoomgesture() {
		return zoomgesture;
	}
	public void setZoomgesture(boolean zoomgesture) {
		this.zoomgesture = zoomgesture;
	}
	
}
