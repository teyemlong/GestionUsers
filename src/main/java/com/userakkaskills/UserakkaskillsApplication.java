package com.userakkaskills;

import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class UserakkaskillsApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserakkaskillsApplication.class, args);
	}
	
	@Bean
	CommandLineRunner start(com.userakkaskills.service.AccountService accountService){
		return args->{
			accountService.saveRole(new com.userakkaskills.entities.AppRole(null, "USER"));
			accountService.saveRole(new com.userakkaskills.entities.AppRole(null, "ADMIN"));
			Stream.of("user1","user2","user3","admin").forEach(u->{
				accountService.saveuser(u, "1234", "1234");
			});
			accountService.addRoleToUser("admin", "ADMIN");
			
		};
		
	}
	@Bean
	public BCryptPasswordEncoder getBCPE(){
		return new BCryptPasswordEncoder();
	}

}

