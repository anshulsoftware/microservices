package com.moody.moon.limitsservice;

import org.springframework.stereotype.Component;

@Component
public class LimitsConfiguration {
	private int minimum;
	private int maximum;
	
	protected LimitsConfiguration() {
		
	}
	
	public LimitsConfiguration(int minimum, int maximum) {
		super();
		this.maximum = maximum;
		this.minimum = minimum;
	}
	
	public int getminimum() {
		return minimum;
	}
	public void setminimum(int minimum) {
		this.minimum = minimum;
	}
	public int getMaximum() {
		return maximum;
	}
	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}
	
	

}
