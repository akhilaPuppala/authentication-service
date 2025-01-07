package com.demo.authentication_service.service;

import org.springframework.stereotype.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.demo.authentication_service.dao.UserCredentialsDao;
import com.demo.authentication_service.dao.entity.UserCredentialsEntity;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserCredentialsDao userCredentialsDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserCredentialsEntity> user = userCredentialsDao.findByName(username);
		return user.map(CustomUserDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("Username/password not valid!"));
	}
}