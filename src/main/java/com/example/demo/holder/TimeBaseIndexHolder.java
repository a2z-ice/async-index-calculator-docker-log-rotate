package com.example.demo.holder;

import java.util.List;

import com.example.demo.component.DateTimeComponent;
import com.example.demo.model.Tick;
import com.example.demo.repository.TickRepository;

public class TimeBaseIndexHolder extends IndexHolder {
	
	final TickRepository tickRepository;
	public TimeBaseIndexHolder(TickRepository tickRepository, DateTimeComponent dateTimeComponent) {
		super(dateTimeComponent);
		this.tickRepository = tickRepository;
	}
	@Override
	public List<Tick> getTickCollection() {
		return tickRepository.getAllTicks();
	}
}
