/**
 * 
 */
package com.userakkaskills.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.userakkaskills.dao.AppRoleRepository;
import com.userakkaskills.dao.AppUserRepository;
import com.userakkaskills.entities.AppRole;
import com.userakkaskills.entities.AppUser;

/**
 * @author Robert
 *
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService {
	@Autowired
	private AppUserRepository appUserRepository;
	@Autowired
	private AppRoleRepository appRoleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	/* (non-Javadoc)
	 * @see com.userakkaskills.service.AccountService#saveuser(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public AppUser saveuser(String username, String password, String confirmedPassword) {
		AppUser user=appUserRepository.findByUsername(username);
		if(user!=null) throw new RuntimeException("User already exist");
		if(!password.equals(confirmedPassword)) throw new RuntimeException("Confirm your password");
		AppUser appUser=new AppUser();
		appUser.setUsername(username);
		appUser.setActived(true);
		appUser.setPassword(bCryptPasswordEncoder.encode(confirmedPassword));
		appUserRepository.save(appUser);
		addRoleToUser(username,"USER");
		return appUser;
	}

	/* (non-Javadoc)
	 * @see com.userakkaskills.service.AccountService#saveRole(com.userakkaskills.entities.AppRole)
	 */
	@Override
	public AppRole saveRole(AppRole role) {
		
		return appRoleRepository.save(role);
	}

	/* (non-Javadoc)
	 * @see com.userakkaskills.service.AccountService#loadUserByUserName(java.lang.String)
	 */
	@Override
	public AppUser loadUserByUserName(String username) {
		
		return appUserRepository.findByUsername(username);
	}

	/* (non-Javadoc)
	 * @see com.userakkaskills.service.AccountService#addRoleToUser(java.lang.String, java.lang.String)
	 */
	@Override
	public void addRoleToUser(String username, String roleName) {
		AppUser appUser=appUserRepository.findByUsername(username);
		AppRole appRole=appRoleRepository.findByRoleName(roleName);
		appUser.getRole().add(appRole);

	}

}
