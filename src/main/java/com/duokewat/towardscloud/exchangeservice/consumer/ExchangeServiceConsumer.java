package com.duokewat.towardscloud.exchangeservice.consumer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.duokewat.towardscloud.exchangeservice.view.ExchangeRequest;

import lombok.extern.slf4j.Slf4j;



@Service
@Slf4j
public class ExchangeServiceConsumer {
	
	@Value( "${exchange.host}" )
	private String apiHost;
	
	@Value( "${exchange.key}" )
	private String key;
	
	@Value( "${exchange.url}" )
	private String url;
	
	
	public BigDecimal getExchangedDetail(ExchangeRequest request) {
		log.debug("Inside getExchangedDetail()");
		
		log.debug("apiHost '{}'",apiHost);
		log.debug("key '{}'",key);
		log.debug("url '{}'",url);
		
		
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		interceptors.add(new HeaderRequestInterceptor("ContentType", MediaType.APPLICATION_JSON_VALUE));
		interceptors.add(new HeaderRequestInterceptor("X-RapidAPI-Host", apiHost));
		interceptors.add(new HeaderRequestInterceptor("RapidAPI-Key", key));
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setInterceptors(interceptors);
		
		String finalUrl = getUrl(request);
		log.debug("finalUrl '{}'",finalUrl);
		
		BigDecimal exchangeAmount = new BigDecimal(restTemplate.getForObject(finalUrl,String.class));
		log.debug("Post Cloud API call");
		log.debug("exchangeAmount '{}'",exchangeAmount);
		
		return exchangeAmount;
	}
	
	private String getUrl(ExchangeRequest exchange) {
		return String.format(url, exchange.getFrom(), exchange.getTo());
	}
}
