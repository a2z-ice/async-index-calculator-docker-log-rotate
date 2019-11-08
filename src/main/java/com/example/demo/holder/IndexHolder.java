package com.example.demo.holder;

import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.component.DateTimeComponent;
import com.example.demo.component.IndexCalculator;
import com.example.demo.model.Tick;

public abstract class IndexHolder {
	final DateTimeComponent dateTimeComponent;
	
	public IndexHolder(DateTimeComponent dateTimeComponent) {
		this.dateTimeComponent = dateTimeComponent;
	}
	
	abstract List<Tick> getTickCollection();

	public List<Tick> getLastSecondsTickInSecond() {
		return getTickCollection().stream()
				.filter(tick -> 
						(Math.abs(dateTimeComponent.getCurrentTime() - tick.getTimestamp())/1000) < IndexCalculator.MAX_TICK_AGE)
				.collect(Collectors.toList());
	}

}
