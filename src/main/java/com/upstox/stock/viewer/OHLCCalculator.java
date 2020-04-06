package com.upstox.stock.viewer;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.upstox.stock.viewer.model.Bar;
import com.upstox.stock.viewer.model.Trade;

/**
 * 
 * @author shishir.sarkar
 * FSM calculator to calculates the OHLC and volumes records. 
 */
@Component
public class OHLCCalculator {

	private static final String SYMBOL = "XXBTZUSD";
	private static final String OHLC_NOTIFY = "ohlc_notify";
	private ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
			false);
	private Bar bar = new Bar();
	private static int COUNTER = 0;
	private static int BARNUMBER = 0;
	private Logger log = LoggerFactory.getLogger(OHLCCalculator.class);
	/**
	 * (FSM) computes OHLC packets based on 15 seconds (interval) and constructs 'BAR' chart data pojo class.
	 * @param Trades input data
	 * @return Calculated bar chart data. 
	 */
	public final Bar calculateOHLC(final String nextTradeData) {
		try {
			Optional.ofNullable(nextTradeData).orElseThrow(Exception::new);
			Trade readValue = objectMapper.readValue(nextTradeData, Trade.class);
			if (COUNTER <= 1) {
				log.info("calculating Initial OHLC record");
				bar = calculateInitialOHLCData(readValue);
				log.info("Initial OHLC record calculation done");
			} else if (COUNTER >1 && COUNTER <= 14) {
				log.info("calculating each OHLC record");
				bar = calculateOHLCeverySec(objectMapper.readValue(nextTradeData, Trade.class));
				log.info(" 1 sec OHLC record calculation done");
			} else if (COUNTER == 15) {
				log.info("calculating every 15 OHLC record");
				bar = calculateOHLin15Sec(objectMapper.readValue(nextTradeData, Trade.class));
				log.info("15 sec OHLC record calculation done");
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			return Optional.of(new Bar()).get();
		}
		return bar;
	}

	/*
	 * Calculated OHLC record for 2 to 14 second of interval
	 */
	private Bar calculateOHLCeverySec(final Trade trade) {
		if (bar.getH() < trade.getP()) {
			bar.setH(trade.getP());
		} else if (bar.getL() > trade.getP()) {
			bar.setL(trade.getP());
		}
		bar.setVolume(bar.getVolume() + trade.getQ());
		COUNTER++;
		log.info("************************calculated OHLC record of counter --> "+(COUNTER-1)+"--********************************");
		log.info(bar.toString());
		log.info("***********************************************************************************");
		
		return bar;
	}

	/*
	 * Calculated OHLC record for 15th second of interval
	 */
	private Bar calculateOHLin15Sec(final Trade trade) {
		if (bar.getH() < trade.getP()) {
			bar.setH(trade.getP());
		} else if (bar.getL() > trade.getP()) {
			bar.setL(trade.getP());
		}
		bar.setC(bar.getL());
		bar.setEvent(OHLC_NOTIFY);
		bar.setSymbol(SYMBOL);
		bar.setVolume(bar.getVolume() + trade.getQ());
		COUNTER = 1;
		log.info("************************calculated OHLC record of counter --> "+(COUNTER+14)+"--********************************");
		log.info(bar.toString());
		log.info("***********************************************************************************");
		return bar;

	}

	/*
	 * Calculated OHLC 1 record.
	 */
	private Bar calculateInitialOHLCData(final Trade trade) {
		bar.setBarNum(++BARNUMBER);
		bar.setO(trade.getP());
		bar.setH(trade.getP());
		bar.setL(trade.getP());
		bar.setC(0.0f);
		bar.setEvent(OHLC_NOTIFY);
		bar.setSymbol(SYMBOL);
		if (BARNUMBER == 1) {
			/* For initial OHLC calculation */
			bar.setVolume(trade.getP());
			COUNTER = 2;
		} else {
			/* For every new Bar start from after 15 Sec */
			bar.setVolume(bar.getVolume() + trade.getQ());
			COUNTER++;
		}
		log.info("************************calculated OHLC record of counter --> "+(COUNTER-1)+"--********************************");
		log.info(bar.toString());
		log.info("***********************************************************************************");
		return bar;
	}

}
