package bp.PAI_jwt.facade;

import bp.PAI_jwt.composite.*;
import bp.PAI_jwt.config.JwtTokenUtil;
import bp.PAI_jwt.dto.UserDTO;
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

// Tydzień 3, Wzorzec Facade
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

    public String authenticate(JwtRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);

        //Tydzień 2, Wzorzec Composite
        // Kod tworzy trzy obiekty tekstowe reprezentujące nagłówek, treść główną i stopkę, a następnie tworzy obiekt drukujący, który wykorzystuje te trzy elementy tekstowe do wydruku.
        // Wykorzystanie kompozycji pozwala na elastyczne zarządzanie różnymi częściami tekstu w celu generowania i wyświetlania kompletnych komunikatów.
        Text headerText = new HeaderText("Sukces!!!");
        Text mainText = new MainText("Udało się zalogować!");
        Text footerText = new FooterText("Czerp radość z korzystania z naszej aplikacji");
        Text printText = new PrintText(headerText, mainText, footerText);
        printText.print();
        //Koniec, Tydzień 2, Wzorzec Composite

        return token;
    }

    public User register(UserDTO user) throws Exception {
        return userDetailsService.save(user);
    }
}
//Koniec, Tydzień 3, Wzorzec Facade
