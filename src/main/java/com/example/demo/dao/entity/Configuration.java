package com.example.demo.dao.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.demo.component.Constants.ConfigurationState;

@Entity
@Table(name = "pricing_info")
public class Configuration implements Serializable {

	private static final long serialVersionUID = -231039811162141917L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "version", unique = true)
	@Size(max = 100)
	private String version;

	@Column(name = "configuration")
	private String configuration;// NOSONAR

	@Column(name = "state")
	@Enumerated(EnumType.STRING)
	private ConfigurationState state;

	@CreationTimestamp
	@Column(name = "created_at")
	private Date createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private Date updatedAt;

	public Configuration() {
		super();
	}

	public Configuration(String version, String configuration, ConfigurationState state) {
		super();
		this.version = version;
		this.configuration = configuration;
		this.state = state;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getConfiguration() {
		return configuration;
	}

	public void setConfiguration(String configuration) {
		this.configuration = configuration;
	}

	public ConfigurationState getState() {
		return state;
	}

	public void setState(ConfigurationState state) {
		this.state = state;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "Configuration [id=" + id + ", version=" + version + ", configuration=" + configuration + ", state="
				+ state + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
}
