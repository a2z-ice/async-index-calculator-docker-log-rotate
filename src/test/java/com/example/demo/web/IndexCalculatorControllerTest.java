package com.example.demo.web;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.component.DateTimeComponent;
import com.example.demo.model.Tick;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class IndexCalculatorControllerTest {

	@MockBean
	private DateTimeComponent dateTimeComponent;
	
	
    @Autowired
    private MockMvc mockMvc;	
	
	@Test
	@DisplayName("POST /ticks - Created")
	void textCreateTicks() throws Exception {

        Date tickTime = Date.from( LocalDateTime.of(2019, Month.NOVEMBER, 6, 22, 1, 10)
        					.atZone( ZoneId.systemDefault()).toInstant());
        
        Date mockCurrentTime = Date.from( LocalDateTime.of(2019, Month.NOVEMBER, 6, 22, 1, 0)
								.atZone( ZoneId.systemDefault()).toInstant());

        long mockCurrentTimestamp = mockCurrentTime.getTime();
        System.out.println(mockCurrentTimestamp);
        
        doReturn(mockCurrentTime.getTime()).when(dateTimeComponent).getCurrentTime();
        
        System.out.println(dateTimeComponent.getCurrentTime());
        
       
		Tick tick = new Tick("IBM.N", 143.82, tickTime.getTime());
		
		mockMvc.perform(post("/ticks")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(tick)))
				.andExpect(status().isCreated())
		;
	
	}
	
	@Test
	@DisplayName("POST /ticks -  No content")
	void textDonotCreateTicketWithNoContentStatus() throws Exception {

        Date tickTime = Date.from( LocalDateTime.of(2019, Month.NOVEMBER, 6, 22, 1, 0)
        					.atZone( ZoneId.systemDefault()).toInstant());
        
        Date mockCurrentTime = Date.from( LocalDateTime.of(2019, Month.NOVEMBER, 6, 22, 2, 2)
								.atZone( ZoneId.systemDefault()).toInstant());

        
        doReturn(mockCurrentTime.getTime()).when(dateTimeComponent).getCurrentTime();
        
        
       
		Tick tick = new Tick("IBM.N", 143.82, tickTime.getTime());
		
		mockMvc.perform(post("/ticks")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(tick)))
				.andExpect(status().isNoContent());
	
	}
	
	
	
	
    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
