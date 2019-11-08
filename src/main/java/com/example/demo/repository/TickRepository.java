package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.Statistics;
import com.example.demo.model.Tick;

public interface TickRepository {

	Tick saveTick(Tick tick);

	List<Tick> getAllTicks();

	void setTimeBasedStatistics(Statistics tstatistics);

	void setInstrumentBasedStatistics(String instrument, Statistics statistics);

	Statistics getInstrumentBasedStatistics(String instrument);

	Statistics getTimeBaseStatistics();

}
