package com.example.demo.web;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Tick;
import com.example.demo.service.IndexCalculatorService;

@RestController
public class IndexCalculatorController {
	
	private final IndexCalculatorService indexCalculatorService;
	
	public IndexCalculatorController(IndexCalculatorService indexCalculatorService) {
		this.indexCalculatorService = indexCalculatorService;
	}

	@PostMapping("/ticks")
	public ResponseEntity<?> saveTick(@RequestBody Tick tick) {
		
		 ResponseEntity<Object> orElse = indexCalculatorService.trackTrick(tick)
			.map(trackedTick -> {
				try {
					return ResponseEntity
						.created(new URI("/ticks"))
						.build();
				} catch (URISyntaxException e) {
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
				}
			}).orElse(ResponseEntity.noContent().build());
		 return orElse;
	}
	
	@GetMapping("/statistics")
	public ResponseEntity<?> getState() {
		return ResponseEntity
				.ok()
				.body(indexCalculatorService.getTimebaseStatistics());
	}	
	
	@GetMapping("/statistics/{instrument_identifier}")
	public ResponseEntity<?> getProduct(@PathVariable String instrument_identifier) {
		return ResponseEntity
				.ok()
				.body(indexCalculatorService.getStaticByInstrument(instrument_identifier));
	}	
	
	
	
}
