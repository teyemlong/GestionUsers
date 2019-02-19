/**
 * 
 */
package com.userakkaskills.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.userakkaskills.dao.AppUserRepository;
import com.userakkaskills.entities.AppUser;
import com.userakkaskills.service.AccountService;

/**
 * @author Robert
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	private AccountService  accountService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		AppUser user= accountService.loadUserByUserName(username);
		if(user==null) throw new UsernameNotFoundException("User Invaid!!");
		Collection<GrantedAuthority> authorities=new ArrayList<>();
		user.getRole().forEach(r->{
			authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
		});
		
		return new User(user.getUsername(),user.getPassword(),authorities);
	}

}
