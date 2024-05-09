package bp.PAI_jwt.service;

import bp.PAI_jwt.adapter.UserDTOAdapter;
import bp.PAI_jwt.functionalInterfaces.UserCreator;
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
    private final UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public JwtUserDetailsService(UserRepository userDao) {
        this.userRepository = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            //Tydzień 9, 6. Dodaj zwracanie wyjątków zamiast kodów błędów (3 wystąpienia)
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), new ArrayList<>());
    }

    public User save(UserDTO userDTO) {
        User user = convertUserDTOToUser(userDTO);
        encodeUserPassword(user);
        return userRepository.save(user);
    }

    private User convertUserDTOToUser(UserDTO userDTO) {
        //Tydzień 2, Wzorzec Adapter
        // Metoda `save()` przyjmuje obiekt `UserDTO`, który jest konwertowany na obiekt typu `User` za pomocą klasy `UserDTOAdapter`, wykorzystującej wzorzec adaptera.
        // Następnie hasło użytkownika jest szyfrowane i zapisywane w bazie danych przy użyciu interfejsu DAO (Data Access Object).
        UserDTOAdapter userDTOAdapter = new UserDTOAdapter(userDTO);
        return (User) userDTOAdapter.clone();
        //Koniec, Tydzień 2, Wzorzec Adapter
    }

    private void encodeUserPassword(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }
}