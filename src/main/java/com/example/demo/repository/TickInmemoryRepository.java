package com.example.demo.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Statistics;
import com.example.demo.model.Tick;

@Repository
public class TickInmemoryRepository implements TickRepository {

	private static final List<Tick> tickInmemoryDb = new ArrayList<>();
	private static Statistics timeBasedStatistics = new Statistics();
	private static Map<String,Statistics > instrumentBasedStatistics = new HashMap<>();
	
	@Override
	public Tick saveTick(Tick tick) {
		tickInmemoryDb
		.add(tick);
		return tick;
	}
	
	@Override
	public List<Tick> getAllTicks() {
		return tickInmemoryDb;
	}
	
	@Override
	public void setTimeBasedStatistics(Statistics statistics) {
			timeBasedStatistics = statistics;
	}
	
	@Override
	public void setInstrumentBasedStatistics(String instrument, Statistics statistics) {
		instrumentBasedStatistics.put(instrument,statistics);
	}
	
	@Override
	public Statistics getInstrumentBasedStatistics(String instrument) {
		return instrumentBasedStatistics.getOrDefault(instrument, new Statistics());
	}
	
	@Override
	public Statistics getTimeBaseStatistics() {
		return timeBasedStatistics;
	}
	
}
