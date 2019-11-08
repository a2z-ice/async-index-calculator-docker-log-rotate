package com.example.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.component.DateTimeComponent;
import com.example.demo.model.Tick;
import com.example.demo.repository.TickRepository;

@Service
public class IndexCalculatorServiceImpl implements IndexCalculatorService {
	
	private static final int MAX_AGE_THRESOLD = 60;
	
	private final TickRepository tickRepository;
	private final DateTimeComponent dateTimeComponent;
	public IndexCalculatorServiceImpl(TickRepository tickRepository, DateTimeComponent dateTimeComponent) {
		this.tickRepository = tickRepository;
		this.dateTimeComponent = dateTimeComponent;
	}
	
	@Override
	public Optional<Tick> trackTrick(Tick tick) {
		long cuttentTimestamp = dateTimeComponent.getCurrentTime();
		long timeDiff = Math.abs(cuttentTimestamp - tick.getTimestamp())/1000; 
		
		if(timeDiff > MAX_AGE_THRESOLD) {
			return Optional.empty();
		} 
		
		tickRepository.saveTick(tick);
		return Optional.of(tick);
	}
}
