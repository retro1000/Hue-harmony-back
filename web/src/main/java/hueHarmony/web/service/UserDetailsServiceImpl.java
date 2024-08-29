package hueHarmony.web.service;

import hueHarmony.web.repository.UserRepository;
import hueHarmony.web.dto.UserAuthDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAuthDto user = userRepository.findUserByUserName(username);

        return new User(
                username,
                user.getPassword(),
                user.getRoles().stream().map(role->new SimpleGrantedAuthority("ROLE_"+role.toUpperCase())).collect(Collectors.toList())
        );
    }
}
