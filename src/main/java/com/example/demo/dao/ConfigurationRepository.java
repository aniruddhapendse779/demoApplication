package com.example.demo.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.component.Constants.ConfigurationState;
import com.example.demo.dao.entity.Configuration;

/**
 * @author gayatri.laghate
 * 
 *         Repository class for Configuration entity.
 */
@Repository
public interface ConfigurationRepository extends PagingAndSortingRepository<Configuration, Long> {

	public Configuration findByid(Long id);

	public Configuration findByVersion(String version);

	// @formatter:off
	@Query("SELECT a "
			+ " FROM Configuration a " 
			+ " WHERE a.state = 'ACTIVE'")
	// @formatter:on
	public Configuration findActiveConfigurationOfType();

	// @formatter:off
	@Query("SELECT a "
			+ " FROM Configuration a " 
			+ " WHERE a.state = ?1")
	// @formatter:on
	public Page<Configuration> findByState(ConfigurationState state, Pageable pageable);
}
