package bp.PAI_jwt.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import bp.PAI_jwt.flyweight.UserFlyweight;
import bp.PAI_jwt.model.Favorite;
import bp.PAI_jwt.model.Track;
import bp.PAI_jwt.model.User;
import bp.PAI_jwt.repository.FavoriteRepository;
import bp.PAI_jwt.repository.TrackRepository;
import bp.PAI_jwt.repository.UserRepository;  // Make sure to import the appropriate repository
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

    @GetMapping("")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> users = new ArrayList<>();
            userRepository.findAll().forEach(users::add);

            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

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
        Optional<User> user2 = UserFlyweight.findByUsername(username, userRepository);
        Optional<User> user = Optional.ofNullable(userRepository.findByUsername(username));

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




    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private TrackRepository trackRepository;

    @PostMapping("/{userId}/favorites/{trackId}")
    public ResponseEntity<Favorite> createFavorite(@PathVariable long userId, @PathVariable long trackId) {
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
    }

    @PostMapping("/self/favorites/{trackId}")
    public ResponseEntity<Favorite> addSelfFavorite(@PathVariable long trackId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<User> userOptional = Optional.ofNullable(userRepository.findByUsername(username));
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
    }

    @GetMapping("/{userId}/favorites")
    public ResponseEntity<List<Favorite>> getFavoritesByUser(@PathVariable long userId) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            List<Favorite> favorites = favoriteRepository.findByUser(user);
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
            List<Favorite> favorites = favoriteRepository.findByUser(user);
            return new ResponseEntity<>(favorites, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // User not found
        }
    }


    @DeleteMapping("/{userId}/favorites/{trackId}")
    public ResponseEntity<HttpStatus> deleteFavorite(@PathVariable long userId, @PathVariable long trackId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Track> trackOptional = trackRepository.findById(trackId);

        if (userOptional.isPresent() && trackOptional.isPresent()) {
            User user = userOptional.get();
            Track track = trackOptional.get();

            Optional<Favorite> existingFavorite = favoriteRepository.findByUserAndTrack(user, track);

            if (existingFavorite.isPresent()) {
                favoriteRepository.delete(existingFavorite.get());
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

            Optional<Favorite> existingFavorite = favoriteRepository.findByUserAndTrack(user, track);

            if (existingFavorite.isPresent()) {
                favoriteRepository.delete(existingFavorite.get());
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Favorite not found
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // User or Track not found
        }
    }

}

