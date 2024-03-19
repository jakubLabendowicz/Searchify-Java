package bp.PAI_jwt.service;

import bp.PAI_jwt.adapter.UserDTOAdapter;
import bp.PAI_jwt.model.User;
import bp.PAI_jwt.dto.UserDTO;
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
    public User save(UserDTO userDTO) {
        //Tydzień 2, Wzorzec Adapter
        // Metoda `save()` przyjmuje obiekt `UserDTO`, który jest konwertowany na obiekt typu `User` za pomocą klasy `UserDTOAdapter`, wykorzystującej wzorzec adaptera.
        // Następnie hasło użytkownika jest szyfrowane i zapisywane w bazie danych przy użyciu interfejsu DAO (Data Access Object).
        UserDTOAdapter userDTOAdapter = new UserDTOAdapter(userDTO);
        User user = (User) userDTOAdapter.clone();
        //Koniec, Tydzień 2, Wzorzec Adapter
        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        return userDao.save(user);
    }
}