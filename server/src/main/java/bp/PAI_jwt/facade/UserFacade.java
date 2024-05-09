package bp.PAI_jwt.facade;

import bp.PAI_jwt.composite.*;
import bp.PAI_jwt.config.JwtTokenUtil;
import bp.PAI_jwt.dto.UserDTO;
import bp.PAI_jwt.functionalInterfaces.UserCreator;
import bp.PAI_jwt.model.JwtRequest;
import bp.PAI_jwt.model.User;
import bp.PAI_jwt.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

// Tydzień 4, Wzorzec Facade
// Klasa `UserFacade` jest fasadą umożliwiającą uwierzytelnianie użytkowników oraz rejestrację nowych użytkowników za pomocą Spring Security i JWT.
// Dodatkowo wykorzystuje wzorzec Composite do generowania i wyświetlania kompletnych komunikatów.
@Component
public class UserFacade {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    //Tydzień 9, 4. Dostosuj funkcje tak by były tylko na jednym poziomie abstrakcji, kolejno wywoływane funkcje coraz bardziej szczegółowe (top to botom)
    public String authenticate(JwtRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (DisabledException e) {
            //Tydzień 9, 6. Dodaj zwracanie wyjątków zamiast kodów błędów (3 wystąpienia)
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            //Tydzień 9, 6. Dodaj zwracanie wyjątków zamiast kodów błędów (3 wystąpienia)
            throw new Exception("INVALID_CREDENTIALS", e);
        }
        authenticatePrintInfo();
        return generateToken(authenticationRequest.getUsername());
    }

    private void authenticatePrintInfo() {
        //Tydzień 2, Wzorzec Composite
        // Kod tworzy trzy obiekty tekstowe reprezentujące nagłówek, treść główną i stopkę, a następnie tworzy obiekt drukujący, który wykorzystuje te trzy elementy tekstowe do wydruku.
        // Wykorzystanie kompozycji pozwala na elastyczne zarządzanie różnymi częściami tekstu w celu generowania i wyświetlania kompletnych komunikatów.
        Text headerText = new HeaderText("Sukces!!!");
        Text mainText = new MainText("Udało się zalogować!");
        Text footerText = new FooterText("Czerp radość z korzystania z naszej aplikacji");
        Text printText = new PrintText(headerText, mainText, footerText);
        printText.print();
        //Koniec, Tydzień 2, Wzorzec Composite
    }

    private String generateToken(String username) throws Exception {
        final UserDetails userDetails = getUserDetails(username);
        return jwtTokenUtil.generateToken(userDetails);
    }

    private UserDetails getUserDetails(String username) throws Exception {
        return userDetailsService.loadUserByUsername(username);
    }

    public User register(UserDTO user) throws Exception {
        // Tydzień 10, utwórz 3 interfejsy funkcyjne, wykonaj ich implementacje i napisz kod używający instancji tych interfejsów poprzez zastosowanie wyrażenia lambda
        UserCreator userCreator = (userDTO) -> userDetailsService.save(userDTO);
        return userCreator.createUser(user);
        //Koniec, Tydzień 10, utwórz 3 interfejsy funkcyjne, wykonaj ich implementacje i napisz kod używający instancji tych interfejsów poprzez zastosowanie wyrażenia lambda
    }
}
//Koniec, Tydzień 4, Wzorzec Facade
