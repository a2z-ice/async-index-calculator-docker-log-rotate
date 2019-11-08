package com.example.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.component.DateTimeComponent;
import com.example.demo.component.IndexCalculator;
import com.example.demo.model.Statistics;
import com.example.demo.model.Tick;
import com.example.demo.repository.TickRepository;

@Service
public class IndexCalculatorServiceImpl implements IndexCalculatorService {
	
	
	
	private final TickRepository tickRepository;
	private final DateTimeComponent dateTimeComponent;
	private final IndexTaskService indexTaskService;
	
	public IndexCalculatorServiceImpl(TickRepository tickRepository, DateTimeComponent dateTimeComponent, IndexTaskService indexTaskService) {
		this.tickRepository = tickRepository;
		this.dateTimeComponent = dateTimeComponent;
		this.indexTaskService = indexTaskService;
	}
	
	@Override
	public Optional<Tick> trackTrick(Tick tick) {
		long cuttentTimestamp = dateTimeComponent.getCurrentTime();
		long timeDiff = Math.abs(cuttentTimestamp - tick.getTimestamp())/1000; 
		
		System.out.println(">>" + cuttentTimestamp);
		
		if(timeDiff > IndexCalculator.MAX_TICK_AGE) {
			return Optional.empty();
		} 
		
		tickRepository.saveTick(tick);
		indexTaskService.runStatisticsCalculator();
		
		return Optional.of(tick);
	}
	
	@Override
	public Statistics getStaticByInstrument(String instrumentIdentifier) {
		return tickRepository.getInstrumentBasedStatistics(instrumentIdentifier);
	}
	@Override
	public Statistics getTimebaseStatistics() {
		return tickRepository.getTimeBaseStatistics();
	}
	
	
	
	

}
