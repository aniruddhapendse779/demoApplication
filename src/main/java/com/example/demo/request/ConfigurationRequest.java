package com.example.demo.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ConfigurationRequest {

	@NotEmpty(message = "cannot be empty")
	@Size(max = 100)
	protected String version;

	@NotEmpty
	protected String configuration;
}