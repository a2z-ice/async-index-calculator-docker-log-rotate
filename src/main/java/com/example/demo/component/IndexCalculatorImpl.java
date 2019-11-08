package com.example.demo.component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.holder.IndexHolder;
import com.example.demo.model.Tick;

public class IndexCalculatorImpl implements IndexCalculator {
	final List<Tick> priceSortedTickList;
	public IndexCalculatorImpl(IndexHolder indexHolder) {
		this.priceSortedTickList = indexHolder.getLastSecondsTickInSecond().stream()
		.sorted(Comparator.comparing(Tick::getPrice))
		.collect(Collectors.toList());
	}
	
	@Override
	public Double min() {
		return priceSortedTickList.isEmpty() ? 0d : priceSortedTickList.get(0).getPrice();
	}

	@Override
	public Double max() {
		return priceSortedTickList.isEmpty() ? 0d : priceSortedTickList.get(priceSortedTickList.size() -1 ).getPrice();
	}

	@Override
	public Double avg() {
		Double sum = 0d;
	    for (Tick tick : priceSortedTickList) {
	        sum += tick.getPrice();
	    }
		return sum == 0d ? 0d : sum/priceSortedTickList.size();
	}

	@Override
	public int count() {
		return priceSortedTickList.size();
	}

	
	
	

}
