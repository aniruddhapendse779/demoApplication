package com.example.demo.response;

import java.io.Serializable;
import java.util.Date;

import com.example.demo.component.Constants.ConfigurationState;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ConfigurationResponse implements Serializable {

	private static final long serialVersionUID = 3191282676504833365L;
	private Long id;
	private String version;
	private ConfigurationState state;
	private Object configurationObject;// NOSONAR
	private Date lastModified;

}
