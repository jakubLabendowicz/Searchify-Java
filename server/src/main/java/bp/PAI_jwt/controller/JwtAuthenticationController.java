package bp.PAI_jwt.controller;

import bp.PAI_jwt.composite.*;
import bp.PAI_jwt.config.JwtTokenUtil;
import bp.PAI_jwt.facade.UserFacade;
import bp.PAI_jwt.model.JwtRequest;
import bp.PAI_jwt.model.JwtResponse;
import bp.PAI_jwt.dto.UserDTO;
import bp.PAI_jwt.model.User;
import bp.PAI_jwt.observer.LoggingUserObserver;
import bp.PAI_jwt.observer.UserManager;
import bp.PAI_jwt.repository.UserRepository;
import bp.PAI_jwt.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class JwtAuthenticationController {
    @Autowired
    private UserFacade userFacade;

    @Autowired
    private UserRepository userRepository;

    private UserManager userManager = new UserManager();

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        userManager.addObserver(new LoggingUserObserver());


        // Tydzień 4, Wzorzec Facade
        String token = userFacade.authenticate(authenticationRequest);
        ResponseEntity<?> responseEntity = ResponseEntity.ok(new JwtResponse(token));
        //Koniec, Tydzień 4, Wzorzec Facade

        // Pobierz informacje o zalogowanym użytkowniku
        User user = userRepository.findByUsername(authenticationRequest.getUsername());

        // Powiadom obserwatorów o zdarzeniu logowania użytkownika
        userManager.notifyObservers(user);

        return responseEntity;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
        // Tydzień 4, Wzorzec Facade
        return ResponseEntity.ok(userFacade.register(user));
        //Koniec, Tydzień 4, Wzorzec Facade
    }
}
