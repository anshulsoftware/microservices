package com.moody.moon.microservices.currencyexchangeservice;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity(name="EXCHANGE_VALUE")
public class ExchangeValue {
	@Id
	private int id;
	@Column(name ="CURRENCY_FROM" )
	private String from;
	@Column(name ="CURRENCY_TO" )
	private String to;
	@Column(name ="CONVERSION_MULTIPLE" )
	private BigDecimal conversionMultiple;
	private int port;
	
	
	public ExchangeValue() {
		
	}
	
	public ExchangeValue(int id,String from, String to, BigDecimal conversionMultiple) {
		super();
		this.id = id;
		this.conversionMultiple = conversionMultiple;
		this.from = from;
		this.to = to;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getConversionMultiple() {
		return conversionMultiple;
	}

	public void setConversionMultiple(BigDecimal conversionMultiple) {
		this.conversionMultiple = conversionMultiple;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}


}
