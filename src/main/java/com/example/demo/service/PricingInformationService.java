package com.example.demo.service;

import java.util.List;

import com.example.demo.request.ConfigurationRequest;
import com.example.demo.response.ConfigurationResponse;
import com.example.demo.response.PricingInformationResponse;

public interface PricingInformationService {

	public PricingInformationResponse getPricingInformation();
	
	public void createConfiguration(ConfigurationRequest reuquet);
	
	public List<ConfigurationResponse> getActiveConfiguiration();
}
