package com.example.demo.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Tick;

@Repository
public class TickInmemoryRepository implements TickRepository {

	private static Map<String, List<Tick>> tickInmemoryDb = new HashMap<>();
	
	@Override
	public Tick saveTick(Tick tick) {
		String instrument = tick.getInstrument();
		tickInmemoryDb
		.computeIfAbsent(instrument, instrumentName -> new ArrayList<>())
		.add(tick);
		return tick;
	}
}
