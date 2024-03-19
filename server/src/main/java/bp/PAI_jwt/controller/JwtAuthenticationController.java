package bp.PAI_jwt.controller;

import bp.PAI_jwt.composite.*;
import bp.PAI_jwt.config.JwtTokenUtil;
import bp.PAI_jwt.model.JwtRequest;
import bp.PAI_jwt.model.JwtResponse;
import bp.PAI_jwt.dto.UserDTO;
import bp.PAI_jwt.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
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

        return ResponseEntity.ok(new JwtResponse(token));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
        return ResponseEntity.ok(userDetailsService.save(user));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
