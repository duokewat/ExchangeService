package com.duokewat.towardscloud.exchangeservice.view;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@ToString
public class ExchangeRequest {
	private String from;
	private String to;
}
