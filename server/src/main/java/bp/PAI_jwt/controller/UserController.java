package bp.PAI_jwt.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import bp.PAI_jwt.flyweight.UserFlyweight;
import bp.PAI_jwt.mediator.FavoriteMediator;
import bp.PAI_jwt.model.*;
import bp.PAI_jwt.repository.*;
import bp.PAI_jwt.state.FavoriteContext;
import bp.PAI_jwt.template.UserTemplate;
import bp.PAI_jwt.template.UserUpdateTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

/*
    @GetMapping("")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> users = new ArrayList<>();
            userRepository.findAll().forEach(users::add);

            // Tydzień 6, Wzorzec Visitor
            // Ten kod wykonuje iterację po kolekcji użytkowników i wywołuje metodę `accept` na każdym z nich, przekazując odwiedzającego `ElementVisitor`.
            // W efekcie, każdy użytkownik zostaje odwiedzony przez `ElementVisitor`, co pozwala na wykonanie odpowiednich operacji zdefiniowanych w kontekście wzorca Visitor.
            Visitor visitor = new ElementVisitor();
            for (User user : users) {
                user.accept(visitor);
            }
            //Koniec, Tydzień 6, Wzorzec Visitor

            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

 */

    // Tydzień 8 Początek. Zasada podstawienia Liskov uzycie
@GetMapping("")
public ResponseEntity<List<User>> getAllUsers() {
    try {
        List<User> users = new ArrayList<>();

        // Creating instances of User, AdminUser, and GuestUser
        User user = new User("John", "Doe", "johndoe", "password");
        AdminUser adminUser = new AdminUser("Admin", "User", "admin", "adminpass", "admin");
        GuestUser guestUser = new GuestUser("Guest", "User", "guest", "guestpass", true);

        // Adding instances to the list
        users.add(user);
        users.add(adminUser);
        users.add(guestUser);

        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(users, HttpStatus.OK);
    } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
    // Tydzień 8 Koniec. Zasada podstawienia Liskov uzycie

/*
    // Tydzień 4 Wzorzec Iterator Początek - Metoda zwracająca wszystkich użytkowników
    @GetMapping("")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            // Tworzymy listę użytkowników
             List<User> users = new ArrayList<>();

            // Tutaj używamy iteratora UserIterator
            UserIterator iterator = new UserIterator(users);

            List<User> userList = new ArrayList<>();
            // Iterujemy po użytkownikach i dodajemy ich do listy
            for (UserIterator it = iterator; it.hasNext(); ) {
                User user = it.next();
                userList.add(user);
            }

            if (userList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(userList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // Tydzień 4 Wzorzec Iterator Koniec
 */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        Optional<User> userData = userRepository.findById(id);

        if (userData.isPresent()) {
            return new ResponseEntity<>(userData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/self")
    public ResponseEntity<User> getSelf() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        String username = authentication.getName();
        // Tydzień 4, Wzorzec Flyweight
        Optional<User> user = UserFlyweight.findByUsername(username, userRepository);
        //Koniec, Tydzień 4, Wzorzec Flyweight

        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            User _user = userRepository.save(new User(user.getFirstname(), user.getLastname(), user.getUsername(), user.getPassword()));
            return new ResponseEntity<>(_user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        Optional<User> userData = userRepository.findById(id);

        if (userData.isPresent()) {
            User _user = userData.get();
            _user.setFirstname(user.getFirstname());
            _user.setLastname(user.getLastname());
            _user.setUsername(user.getUsername());
            _user.setPassword(user.getPassword());
            return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
        try {
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("")
    public ResponseEntity<HttpStatus> deleteAllUsers() {
        try {
            userRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
/*
    // Tydzień 4 Wzorzec Memento Początek
    // Dodajemy pole do przechowywania stanu Memento
    private FavoriteMemento favoriteMemento;

    // Metoda do zapisywania stanu Memento
    private void saveFavoriteMemento(Optional<User> user, Optional<Track> track) {
        favoriteMemento = new FavoriteMemento(user, track);
    }

    // Metoda do przywracania stanu Memento
    private FavoriteMemento restoreFavoriteMemento() {
        return favoriteMemento;
    }

    @PostMapping("/{userId}/favorites/{trackId}")
    public ResponseEntity<Favorite> createFavorite(@PathVariable long userId, @PathVariable long trackId) {
        try {
            // Zapisujemy stan Memento przed wykonaniem operacji
            saveFavoriteMemento(userRepository.findById(userId), trackRepository.findById(trackId));

            Optional<User> userOptional = userRepository.findById(userId);
            Optional<Track> trackOptional = trackRepository.findById(trackId);

            if (userOptional.isPresent() && trackOptional.isPresent()) {
                User user = userOptional.get();
                Track track = trackOptional.get();

                Optional<Favorite> existingFavorite = favoriteRepository.findByUserAndTrack(user, track);

                if (existingFavorite.isPresent()) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Favorite already exists
                }

                Favorite favorite = new Favorite(user, track);
                favoriteRepository.save(favorite);
                return new ResponseEntity<>(favorite, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // User or Track not found
            }
        } catch (Exception e) {
            // Przywracamy stan Memento w przypadku błędu
            FavoriteMemento memento = restoreFavoriteMemento();
            if (memento != null) {
                // Możemy użyć stanu Memento do obsługi błędu lub logowania
                // Na przykład, możemy wyświetlić informację o użytkowniku i utworze, których dotyczyło żądanie
                Optional<User> user = memento.getUser();
                Optional<Track> track = memento.getTrack();
                if (user.isPresent() && track.isPresent()) {
                    System.out.println("Błąd podczas tworzenia ulubionego utworu dla użytkownika: " + user.get().getUsername() +
                            " i utworu o ID: " + track.get().getId());
                }
            }
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
// Tydzień 4 Wzorzec Memento Koniec

*/

    // Tydzień 5 Wzorzec State Początek

    @Autowired
    private FavoriteUserRepository favoriteUserRepository;

    @Autowired
    private TrackRepository trackRepository;

    @Autowired
    private FavoriteContext favoriteContext;

    @PostMapping("/{userId}/favorites/{trackId}")
    public ResponseEntity<Favorite> createFavorite(@PathVariable long userId, @PathVariable long trackId) throws Exception {
        favoriteContext.setContext(userRepository, favoriteUserRepository, trackRepository, userId, trackId);
        return favoriteContext.createFavorite();
    }
    // Tydzień 5 Wzorzec State Koniec

    // Tydzień 5 Wzorzec Template Początek
    private final UserTemplate userTemplate;

    public UserController(UserRepository userRepository) {
        this.userTemplate = new UserUpdateTemplate(userRepository);
    }

    @PutMapping("/{userId}/update")
    public ResponseEntity<String> updateUser(@PathVariable long userId) {
        return userTemplate.executeOperation(userId);
    }
    // Tydzień 5 Wzorzec Template Koniec


    @Autowired
    private FavoriteMediator favoriteMediator;

    @PostMapping("/self/favorites/{trackId}")
    public ResponseEntity<Favorite> addSelfFavorite(@PathVariable long trackId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<User> userOptional = Optional.ofNullable(userRepository.findByUsername(username));
        if (userOptional.isPresent()) {
            long userId = userOptional.get().getId();
            // Tydzień 5, Wzorzec Mediator
            return favoriteMediator.addSelfFavorite(userId, trackId);
            //Koniec, Tydzień 5, Wzorzec Mediator
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // User not found
        }
    }

    @GetMapping("/{userId}/favorites")
    public ResponseEntity<List<Favorite>> getFavoritesByUser(@PathVariable long userId) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            List<Favorite> favorites = favoriteUserRepository.findByUser(user);
            return new ResponseEntity<>(favorites, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // User not found
        }
    }

    @GetMapping("/self/favorites")
    public ResponseEntity<List<Favorite>> getSelfFavorites() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<User> userOptional = Optional.ofNullable(userRepository.findByUsername(username));

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            List<Favorite> favorites = favoriteUserRepository.findByUser(user);
            return new ResponseEntity<>(favorites, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // User not found
        }
    }

    @Autowired
    private FavoriteUserTrackRepository favoriteUserTrackRepository;

    @DeleteMapping("/{userId}/favorites/{trackId}")
    public ResponseEntity<HttpStatus> deleteFavorite(@PathVariable long userId, @PathVariable long trackId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Track> trackOptional = trackRepository.findById(trackId);

        if (userOptional.isPresent() && trackOptional.isPresent()) {
            User user = userOptional.get();
            Track track = trackOptional.get();

            Optional<Favorite> existingFavorite = favoriteUserTrackRepository.findByUserAndTrack(user, track);

            if (existingFavorite.isPresent()) {
                favoriteUserTrackRepository.delete(existingFavorite.get());
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Favorite not found
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // User or Track not found
        }
    }

    @DeleteMapping("/self/favorites/{trackId}")
    public ResponseEntity<HttpStatus> removeSelfFavorite(@PathVariable long trackId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<User> userOptional = Optional.ofNullable(userRepository.findByUsername(username));
        Optional<Track> trackOptional = trackRepository.findById(trackId);

        if (userOptional.isPresent() && trackOptional.isPresent()) {
            User user = userOptional.get();
            Track track = trackOptional.get();

            Optional<Favorite> existingFavorite = favoriteUserTrackRepository.findByUserAndTrack(user, track);

            if (existingFavorite.isPresent()) {
                favoriteUserTrackRepository.delete(existingFavorite.get());
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Favorite not found
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // User or Track not found
        }
    }
}

