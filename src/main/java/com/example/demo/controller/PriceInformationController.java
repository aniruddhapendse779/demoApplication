package com.example.demo.controller;

import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.component.ApiMapping;
import com.example.demo.model.Response;
import com.example.demo.request.ConfigurationRequest;
import com.example.demo.response.ConfigurationResponse;
import com.example.demo.service.impl.PricingInformationServiceImpl;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

//@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class PriceInformationController {

	@Autowired
	private PricingInformationServiceImpl pricingInformationService;

//	@GetMapping(value = { ApiMapping.GET })
//	public ResponseEntity<Response> getPricingInformation() {
//		log.info("API hit successfull");
//		PricingInformationResponse response = pricingInformationService.getPricingInformation();
//		return new ResponseEntity<>(Response.getSuccessResponse(response), HttpStatus.OK);
//	}

	@GetMapping(value = ApiMapping.GET)
	public ResponseEntity<Response> getPricingInformationUsingConfiguration() {
		List<ConfigurationResponse> response = pricingInformationService.getActiveConfiguiration();
		return new ResponseEntity<>(Response.getSuccessResponse(response), HttpStatus.OK);
	}

	@PostMapping(value = ApiMapping.CREATE)
	public ResponseEntity<Response> createConfiguration(@RequestBody @Valid ConfigurationRequest request) {
		pricingInformationService.createConfiguration(request);
		return new ResponseEntity<>(Response.getSuccessResponse(), HttpStatus.OK);
	}

	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2).select().paths(PathSelectors.ant("/api/*"))
				.apis(RequestHandlerSelectors.basePackage("com")).build().apiInfo(apiDetails());
	}

	private ApiInfo apiDetails() {
		return new ApiInfo("Pricing info Api",
				"API's for pricing info cards.<br> In this API collection you can create and retrive configurable pricing info cards",
				"1.0", "Free to use", new springfox.documentation.service.Contact("Aniruddha Pendse",
						"https://localhost:8080", "something.com"),
				"API Liscence", "http://localhost:8080", Collections.emptyList());
	}

}
