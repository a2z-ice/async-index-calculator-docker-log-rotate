package com.example.demo.model;

public class Tick {

	private String instrument; 
	private double price;
	private long timestamp;
	
	public Tick() {
		
	}
	
	public Tick(String instrument, double price, long timestamp) {
		this.instrument = instrument;
		this.price = price;
		this.timestamp = timestamp;
	}
	public String getInstrument() {
		return instrument;
	}
	public void setInstrument(String instrument) {
		this.instrument = instrument;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
}
