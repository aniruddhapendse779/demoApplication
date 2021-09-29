package com.example.demo.response;

import java.util.List;

import com.example.demo.dao.entity.PricingInformation;

import lombok.Data;

@Data
public class PricingInformationResponse {
	
	private List<PricingInformation> info;
}
