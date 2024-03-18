package bp.PAI_jwt.service;

import bp.PAI_jwt.model.User;
import bp.PAI_jwt.model.UserDto;
import bp.PAI_jwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
@Service
public class JwtUserDetailsService implements UserDetailsService {
    private final UserRepository userDao;

    @Autowired
    public JwtUserDetailsService(UserRepository userDao) {
        this.userDao = userDao;
    }

    @Autowired
    private PasswordEncoder bcryptEncoder;
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new
                    UsernameNotFoundException("User not found with username: " +
                    username);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(),new ArrayList<>());
    }
    public User save(UserDto user) {
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        newUser.setFirstname(user.getFirstname());
        newUser.setLastname(user.getLastname());
        return userDao.save(newUser);
    }
}