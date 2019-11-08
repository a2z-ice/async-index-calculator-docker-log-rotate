package com.example.demo.holder;

import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.component.DateTimeComponent;
import com.example.demo.model.Tick;
import com.example.demo.repository.TickRepository;

public class InstrumentBaseIndexHolder extends IndexHolder {

	final TickRepository tickRepository;
	
	private final String instrument;
	
	public InstrumentBaseIndexHolder(String instrument,TickRepository tickRepository,DateTimeComponent dateTimeComponent) {
		super(dateTimeComponent);
		this.instrument = instrument;
		this.tickRepository = tickRepository;
	}
	
	@Override
	public List<Tick> getTickCollection() {
		return tickRepository.getAllTicks()
				.stream()
				.filter(tick -> instrument.equals(tick.getInstrument()))
				.collect(Collectors.toList());
						
	}
}
