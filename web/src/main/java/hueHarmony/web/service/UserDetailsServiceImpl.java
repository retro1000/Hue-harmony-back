package hueHarmony.web.service;

import hueHarmony.web.repository.UserRepository;
import hueHarmony.web.dto.UserAuthDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

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
