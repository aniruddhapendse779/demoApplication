package com.example.demo.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.demo.component.Constants.ConfigurationState;
import com.example.demo.dao.ConfigurationRepository;
import com.example.demo.dao.PricingInformationRepository;
import com.example.demo.dao.entity.Configuration;
import com.example.demo.dao.entity.PricingInformation;
import com.example.demo.dto.PricingInfoConfiguration;
import com.example.demo.dto.PricingInformationDto;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.request.ConfigurationRequest;
import com.example.demo.response.ConfigurationResponse;
import com.example.demo.response.PricingInformationResponse;
import com.example.demo.service.PricingInformationService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PricingInformationServiceImpl implements PricingInformationService {

	@Autowired
	private PricingInformationRepository pricingInformationRepository;

	@Autowired
	private ConfigurationRepository configurationRepository;

	@Override
	public PricingInformationResponse getPricingInformation() {
		List<PricingInformation> info = pricingInformationRepository.findAll();
		PricingInformationResponse response = new PricingInformationResponse();
		response.setInfo(info);
		return response;
	}

	@Override
	public void createConfiguration(ConfigurationRequest request) {
		// Check The Configurations JSON format
		PricingInfoConfiguration pricingInfoConfiguration = getConfigurationObject(request.getConfiguration());
		// Check whether new Configuration have a new Version
		versionExist(request.getVersion());
		// Make the previous configuration Inactive in order to activate new
		// Configuration
		List<Configuration> activeConfiguration = configurationIfExists(ConfigurationState.ACTIVE);
		if (activeConfiguration != null) {
			for (Configuration configuration : activeConfiguration) {
				configuration.setState(ConfigurationState.INACTIVE);
				configurationRepository.save(configuration);
			}
		}
		Iterator<PricingInformationDto> it = pricingInfoConfiguration.getConfiguration().iterator();

		while (it.hasNext()) {
			Gson gson = new Gson();
			String jsonObj = gson.toJson(it.next());
			Configuration newConfiguration = new Configuration(request.getVersion(), jsonObj,
					ConfigurationState.ACTIVE);
			configurationRepository.save(newConfiguration);
		}
	}

	private List<Configuration> configurationIfExists(ConfigurationState state) {
		Page<Configuration> configurations = configurationRepository.findByState(state, PageRequest.of(0, 10));
		if (null == configurations || configurations.isEmpty())
			return null;
		return configurations.getContent();
	}

	private void versionExist(String version) {
		if (version == null) {
			return;
		}
		Configuration exist = configurationRepository.findByVersion(version);
		if (exist != null) {
			log.error(ErrorCode.VERSION_ALREADY_EXIST.getErrorMessage());
			throw new AppException(ErrorCode.VERSION_ALREADY_EXIST);
		}
	}

	private PricingInfoConfiguration getConfigurationObject(String configStr) { // NOSONAR
		ObjectMapper mapper = new ObjectMapper();
		PricingInfoConfiguration pricingInfoConfiguration = new PricingInfoConfiguration();
		List<PricingInformationDto> pricingInfo = null;
		try {
			pricingInfo = mapper.readValue(configStr, new TypeReference<List<PricingInformationDto>>() {
			});
			pricingInfoConfiguration.setConfiguration(pricingInfo);
		} catch (IOException e) {
			log.error(ErrorCode.INVALID_CONFIGURATION_FORMAT.getErrorMessage());
			throw new AppException(ErrorCode.INVALID_CONFIGURATION_FORMAT);
		}
		return pricingInfoConfiguration;
	}

	private PricingInformationDto getSingleConfigurationObject(String configStr) { // NOSONAR
		ObjectMapper mapper = new ObjectMapper();
		PricingInformationDto pricingInfo = null;
		try {
			pricingInfo = mapper.readValue(configStr, new TypeReference<PricingInformationDto>() {
			});
		} catch (IOException e) {
			log.error(ErrorCode.INVALID_CONFIGURATION_FORMAT.getErrorMessage());
			throw new AppException(ErrorCode.INVALID_CONFIGURATION_FORMAT);
		}
		return pricingInfo;
	}

	@Override
	public List<ConfigurationResponse> getActiveConfiguiration() {
		List<Configuration> configuration = configurationIfExists(ConfigurationState.ACTIVE);

		if (null == configuration) {
			log.error(ErrorCode.NO_ACTIVE_CONFIGURATION_FOUND.getErrorMessage());
			throw new AppException(ErrorCode.NO_ACTIVE_CONFIGURATION_FOUND);
		}
		return response(configuration);
	}

	private List<ConfigurationResponse> response(List<Configuration> configuration) {
		List<ConfigurationResponse> response = new ArrayList<>();
		for (Configuration conf : configuration) {
			PricingInformationDto configurationDTO = getSingleConfigurationObject(conf.getConfiguration());
			response.add(new ConfigurationResponse(conf.getId(), conf.getVersion(), conf.getState(), configurationDTO,
					conf.getUpdatedAt()));
		}
		return response;
	}

}
