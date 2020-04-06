package com.upstox.stock.viewer.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.upstox.stock.viewer.OHLCCalculator;
import com.upstox.stock.viewer.ReadFileLineByLineUsingScanner;
import com.upstox.stock.viewer.model.Bar;
import com.upstox.stock.viewer.model.UserResponse;
/**
 * 
 * @author shishir.sarkar
 *
 */
@EnableScheduling
@Configuration
public class SchedulerConfig {
	private ObjectMapper objectMapper = new ObjectMapper();;
	@Autowired
	SimpMessagingTemplate template;
	@Autowired
	private ReadFileLineByLineUsingScanner scan;
	@Autowired
	private OHLCCalculator calc;
	
	private Logger log = LoggerFactory.getLogger(SchedulerConfig.class);
	
	/*
	 * Reads the Trades data input (line by line from JSON), and sends the packet to
	 * the FSM (Finite-State-Machine) thread.
	 */
	@Scheduled(fixedDelay = 1000)
	public void sendAdhocMessages() {
		Bar bar = calc.calculateOHLC(scan.getNextTradeData());
		try {
			template.convertAndSend("/topic/user",
					new UserResponse(objectMapper.writeValueAsString(bar)));
		} catch (MessagingException | JsonProcessingException e) {
			log.error(e.getLocalizedMessage());
		}
	}
}
