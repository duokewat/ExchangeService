package com.duokewat.towardscloud.exchangeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duokewat.towardscloud.exchangeservice.consumer.ExchangeServiceConsumer;
import com.duokewat.towardscloud.exchangeservice.view.ExchangeRequest;
import com.duokewat.towardscloud.exchangeservice.view.ExchangeResponse;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/currency/api/v1")
@Slf4j
public class ExchangeController {
	
	@Autowired
	ExchangeServiceConsumer exchangeServiceConsumer;
	
	@Autowired
	ExchangeResponse exchangeResponse;
	
	@PostMapping("/exchange")
    public ExchangeResponse getExchangedDetail(@RequestBody ExchangeRequest request) {
		log.info("getExchangedDetail() Begins");
	    log.debug("ExchangeRequest {} " , request);
		exchangeResponse.setFrom(request.getFrom());
		exchangeResponse.setTo(request.getTo());
		exchangeResponse.setValue(exchangeServiceConsumer.getExchangedDetail(request));
		
		 log.debug("exchangeResponse {} " , exchangeResponse);
		return exchangeResponse;
    }
	
	@RequestMapping("/ruok")
    public String ruok() {
        return "Yes, I am Okay !";
    } 
}
