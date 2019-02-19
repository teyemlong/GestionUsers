/**
 * 
 */
package com.userakkaskills.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.userakkaskills.entities.AppRole;

/**
 * @author Robert
 *
 */
@RepositoryRestResource
public interface AppRoleRepository extends JpaRepository<AppRole, Long>{
	public AppRole findByRoleName(String rolename);

}
