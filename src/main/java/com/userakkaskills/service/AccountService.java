/**
 * 
 */
package com.userakkaskills.service;

import com.userakkaskills.entities.AppRole;
import com.userakkaskills.entities.AppUser;

/**
 * @author Robert
 *
 */
public interface AccountService {
	public AppUser saveuser(String username, String password, String confirmedPassword);
	public AppRole saveRole(AppRole role);
	public AppUser loadUserByUserName(String username);
	public void addRoleToUser(String username, String roleName);

}
