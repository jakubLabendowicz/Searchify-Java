package bp.PAI_jwt.controller;

import bp.PAI_jwt.composite.*;
import bp.PAI_jwt.config.JwtTokenUtil;
import bp.PAI_jwt.facade.UserFacade;
import bp.PAI_jwt.model.JwtRequest;
import bp.PAI_jwt.model.JwtResponse;
import bp.PAI_jwt.dto.UserDTO;
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

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        // Tydzień 3, Wzorzec Facade
        return ResponseEntity.ok(new JwtResponse(userFacade.authenticate(authenticationRequest)));
        //Koniec, Tydzień 3, Wzorzec Facade
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
        // Tydzień 3, Wzorzec Facade
        return ResponseEntity.ok(userFacade.register(user));
        //Koniec, Tydzień 3, Wzorzec Facade
    }
}
