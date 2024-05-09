package bp.PAI_jwt.controller;

import bp.PAI_jwt.model.Favorite;
import bp.PAI_jwt.model.Track;
import bp.PAI_jwt.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/stream")
public class StreamController {
    // Tydzień 10, pokaż na przykładzie wykorzystanie programowania funkcyjnego w strumieniowym przetwarzaniu 3 kolekcji

    @GetMapping("/users")
    public ResponseEntity<Map<String, Object>> processUserStream() {
        List<User> users = new ArrayList<>();
        users.add(new User("John", "Doe", "johndoe", "password"));
        users.add(new User("Jane", "Doe", "janedoe", "password123"));
        users.add(new User("Alice", "Smith", "alice", "alice123"));

        // Przykładowe operacje na strumieniach
        List<User> filteredUsers = users.stream()
                .filter(user -> user.getFirstname().startsWith("J"))
                .collect(Collectors.toList());

        Map<String, String> usernameMap = users.stream()
                .collect(Collectors.toMap(User::getUsername, User::getFirstname));

        Set<String> usernames = users.stream()
                .map(User::getUsername)
                .collect(Collectors.toSet());

        // Zwracamy wyniki w formie mapy
        Map<String, Object> result = new HashMap<>();
        result.put("filteredUsers", filteredUsers);
        result.put("usernameMap", usernameMap);
        result.put("usernamesSet", usernames);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/tracks")
    public ResponseEntity<Map<String, Object>> processTrackStream() {
        Set<Track> tracks = new HashSet<>();
        tracks.add(new Track("Track 1", "Artist 1", "123456", "preview1", "high", "300000", "image1"));
        tracks.add(new Track("Track 2", "Artist 2", "789012", "preview2", "medium", "240000", "image2"));
        tracks.add(new Track("Track 3", "Artist 3", "345678", "preview3", "low", "180000", "image3"));

        // Przykładowe operacje na strumieniach
        Set<Track> filteredTracks = tracks.stream()
                .filter(track -> track.getPopularity().equals("high"))
                .collect(Collectors.toSet());

        Map<String, String> trackPreviewUrlMap = tracks.stream()
                .collect(Collectors.toMap(Track::getSpotifyId, Track::getPreviewUrl));

        List<String> trackNames = tracks.stream()
                .map(Track::getName)
                .collect(Collectors.toList());

        // Zwracamy wyniki w formie mapy
        Map<String, Object> result = new HashMap<>();
        result.put("filteredTracks", filteredTracks);
        result.put("trackPreviewUrlMap", trackPreviewUrlMap);
        result.put("trackNamesList", trackNames);

        return ResponseEntity.ok(result);
    }


    @GetMapping("/favorites")
    public ResponseEntity<Map<String, Object>> processFavoritesStream() {
        Map<Long, Favorite> favoritesMap = new HashMap<>();

        User user1 = new User("John", "Doe", "johndoe", "password");
        User user2 = new User("Jane", "Doe", "janedoe", "password123");
        Track track1 = new Track("Track 1", "Artist 1", "123456", "preview1", "high", "300000", "image1");
        Track track2 = new Track("Track 2", "Artist 2", "789012", "preview2", "medium", "240000", "image2");

        favoritesMap.put(1L, new Favorite(user1, track1));
        favoritesMap.put(2L, new Favorite(user2, track2));

        // Przykładowe operacje na strumieniach
        List<User> uniqueUsers = favoritesMap.values().stream()
                .map(Favorite::getUser)
                .distinct()
                .collect(Collectors.toList());

        Set<String> favoriteTrackNames = favoritesMap.values().stream()
                .map(favorite -> favorite.getTrack().getName())
                .collect(Collectors.toSet());

        // Zwracamy wyniki w formie mapy
        Map<String, Object> result = new HashMap<>();
        result.put("uniqueUsers", uniqueUsers);
        result.put("favoriteTrackNamesSet", favoriteTrackNames);

        return ResponseEntity.ok(result);
    }


    //Koniec, Tydzień 10, pokaż na przykładzie wykorzystanie programowania funkcyjnego w strumieniowym przetwarzaniu 3 kolekcji
}
