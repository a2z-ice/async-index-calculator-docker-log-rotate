package com.example.demo.service;

import java.util.Optional;

import com.example.demo.model.Tick;

public interface IndexCalculatorService {

	Optional<Tick> trackTrick(Tick tick);

}
