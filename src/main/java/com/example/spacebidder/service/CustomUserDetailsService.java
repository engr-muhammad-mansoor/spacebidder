package com.example.spacebidder.service;



import com.example.spacebidder.config.CustomUserDetails;
import com.example.spacebidder.model.User;
import com.example.spacebidder.repository.ClientRepository;
import com.example.spacebidder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class CustomUserDetailsService implements UserDetailsService {

/*	@Autowired
	private ClientRepository clientRepository;*/
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub

		User user = userRepository.findUserByClientEmail(username);

		if(user == null)
		{
			throw new UsernameNotFoundException("user not found");
		}
		return new CustomUserDetails(user);
	}


}
