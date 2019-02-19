/**
 * 
 */
package com.userakkaskills.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.userakkaskills.entities.AppUser;

import lombok.Data;

/**
 * @author Robert
 *
 */
@RestController
public class UserController {
	@Autowired
	private com.userakkaskills.service.AccountService AccountService;
	@PostMapping("/register")
	public AppUser register(@RequestBody UserForm userForm){
		return AccountService.saveuser(userForm.getUsername(), 
				userForm.getPassword(), userForm.getConfirmedPassword());
	}

}
@Data
class UserForm{
	private String username;
	private String password;
	private String confirmedPassword;
}
