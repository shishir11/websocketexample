/**
 * 
 */
package com.upstox.stock.viewer.model;

import org.springframework.stereotype.Component;

/**
 * @author shishir.sarkar
 *
 */
@Component
public class Bar {
	private String event;
	private String symbol;
	private int barNum;
	private double o;
	private double h;
	private double l;
	private double c;
	private double volume;

	// Getter Methods

	public String getEvent() {
		return event;
	}

	public String getSymbol() {
		return symbol;
	}

	

	// Setter Methods

	public void setEvent(String event) {
		this.event = event;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}


	public void setO(float o) {
		this.o = o;
	}

	public void setH(float h) {
		this.h = h;
	}

	public void setL(float l) {
		this.l = l;
	}

	public void setC(float c) {
		this.c = c;
	}

	public void setVolume(float volume) {
		this.volume = volume;
	}

	public float getBarNum() {
		return barNum;
	}

	

	public double getO() {
		return o;
	}

	public void setO(double o) {
		this.o = o;
	}

	public double getH() {
		return h;
	}

	public void setH(double h) {
		this.h = h;
	}

	public double getL() {
		return l;
	}

	public void setL(double l) {
		this.l = l;
	}

	public double getC() {
		return c;
	}

	public void setC(double c) {
		this.c = c;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public void setBarNum(int barNum) {
		this.barNum = barNum;
	}

	@Override
	public String toString() {
		return "Bar [event=" + event + ", symbol=" + symbol + ", barNum=" + barNum + ", o=" + o + ", h=" + h + ", l="
				+ l + ", c=" + c + ", volume=" + volume + "]";
	}
	
	
}
