package com.example.demo.component;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class DateTimeComponent {
	public long timeDiffInSecond(long time1, long time2) {
		return Math.abs(time1 - time2)/1000 ;
	}
	
	public long getCurrentTime() {
		return Date.from(Instant.now().atZone( ZoneId.systemDefault()).toInstant()).getTime();
	}
}
