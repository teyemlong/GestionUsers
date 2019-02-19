/**
 * 
 */
package com.userakkaskills.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.userakkaskills.entities.AppUser;

/**
 * @author Robert
 *
 */
@RepositoryRestResource
public interface AppUserRepository extends JpaRepository<AppUser, Long>{
	public AppUser findByUsername(String username);

}
