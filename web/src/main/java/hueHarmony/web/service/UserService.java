package hueHarmony.web.service;

import hueHarmony.web.repository.RoleRepository;
import hueHarmony.web.repository.UserRepository;
import hueHarmony.web.dto.UserProfileDto;
import hueHarmony.web.model.Role;
import hueHarmony.web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final FirebaseStorageService firebaseStorageService;

    @Autowired
    public UserService(FirebaseStorageService firebaseStorageService, UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.firebaseStorageService = firebaseStorageService;
    }

    @Transactional
    public void createNewUser(User user){
        Optional<Role> optionalRole = roleRepository.findById(2L);
        if(optionalRole.isEmpty()) throw new RuntimeException("Role with ID 2 not found.");

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = optionalRole.get();
        user.getRoles().add(role);
        userRepository.save(user);
    }

    @Transactional(readOnly=true)
    public boolean isUsernameExists(String username){
        return userRepository.existsByUsername(username);
    }

    @Transactional(readOnly=true)
    public boolean isEmailExists(String email){
        return userRepository.existsByEmail(email);
    }

    public UserProfileDto getUserProfile(String username){
        UserProfileDto userProfileDto = userRepository.getUserProfileByUsername(username);
        if(userProfileDto.getAvatar()!=null && !userProfileDto.getAvatar().isEmpty()){
            userProfileDto.setAvatar(
                    firebaseStorageService.getFileDownloadUrl(userProfileDto.getAvatar(), 1, TimeUnit.DAYS)
            );
        }
        return userProfileDto;
    }
}
