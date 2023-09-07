package com.qdang.global.security;

import com.qdang.application.user.domain.User;
import com.qdang.application.user.port.out.LoadUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

	private final LoadUserPort loadUserPort;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = loadUserPort.loadById(Long.parseLong(username));
		return new PrincipalDetails(user);
	}
}
