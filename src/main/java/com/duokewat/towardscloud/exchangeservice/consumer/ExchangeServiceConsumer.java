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



@Service
public class ExchangeServiceConsumer {
	
	@Value( "${exchange.host}" )
	private String apiHost;
	
	@Value( "${exchange.key}" )
	private String key;
	
	@Value( "${exchange.url}" )
	private String url;
	
	
	public BigDecimal getExchangedDetail(ExchangeRequest request) {
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		interceptors.add(new HeaderRequestInterceptor("ContentType", MediaType.APPLICATION_JSON_VALUE));
		interceptors.add(new HeaderRequestInterceptor("X-RapidAPI-Host", apiHost));
		interceptors.add(new HeaderRequestInterceptor("RapidAPI-Key", key));
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setInterceptors(interceptors);
		
		return new BigDecimal(restTemplate.getForObject(getUrl(request),String.class));
	}
	
	private String getUrl(ExchangeRequest exchange) {
		return String.format(url, exchange.getFrom(), exchange.getTo());
	}
}
