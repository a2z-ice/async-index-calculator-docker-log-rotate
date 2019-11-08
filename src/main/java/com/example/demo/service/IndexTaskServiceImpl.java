package com.example.demo.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.demo.component.DateTimeComponent;
import com.example.demo.component.IndexCalculator;
import com.example.demo.component.IndexCalculatorImpl;
import com.example.demo.holder.IndexHolder;
import com.example.demo.holder.InstrumentBaseIndexHolder;
import com.example.demo.holder.TimeBaseIndexHolder;
import com.example.demo.model.Statistics;
import com.example.demo.repository.TickRepository;
@Service
public class IndexTaskServiceImpl implements IndexTaskService{
	private final TickRepository tickRepository;
	private final DateTimeComponent dateTimeComponent;
	
	public IndexTaskServiceImpl(TickRepository tickRepository, DateTimeComponent dateTimeComponent) {
		this.tickRepository = tickRepository;
		this.dateTimeComponent = dateTimeComponent;
		
	}
	
	@Override
	@Async("asyncExecutor")
	public void runStatisticsCalculator() {
		processTimeBaseStatistics();
		processInstrumentBaseStatistics();
	}
	
	private void processTimeBaseStatistics() {
		IndexHolder timeBaseIndexHolder = new TimeBaseIndexHolder(tickRepository, dateTimeComponent);
		IndexCalculator timeBaseIndexCalculator = new IndexCalculatorImpl(timeBaseIndexHolder);
		tickRepository.setTimeBasedStatistics(getStatisticsFromIndexCalculator(timeBaseIndexCalculator));
		
	}
	
	private void processInstrumentBaseStatistics() {
		tickRepository.getAllTicks().parallelStream().forEach(tick ->  {
			IndexHolder instruemntBaseIndexHolder = new InstrumentBaseIndexHolder(tick.getInstrument(), tickRepository, dateTimeComponent);
			IndexCalculator instrumentBaseIndexCalculator = new IndexCalculatorImpl(instruemntBaseIndexHolder);
			Statistics instrumentBaseStatistics = getStatisticsFromIndexCalculator(instrumentBaseIndexCalculator);
			tickRepository.setInstrumentBasedStatistics(tick.getInstrument(), instrumentBaseStatistics);
		});		
	}
	
	private Statistics getStatisticsFromIndexCalculator(IndexCalculator indexCalculator) {
		return new Statistics(
				indexCalculator.avg(),
				indexCalculator.max(),
				indexCalculator.min(),
				indexCalculator.count()
				);
	}
	
	@Override
	@Scheduled(fixedDelay=10000)
	public void reCalculateStaticJob() {
		System.out.println("Job running....");
		runStatisticsCalculator();
	}
	
	
}
