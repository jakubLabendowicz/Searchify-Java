package bp.PAI_jwt.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import bp.PAI_jwt.bridge.BasicTrackOperations;
import bp.PAI_jwt.bridge.ExtendedTrackOperations;
import bp.PAI_jwt.bridge.TrackOperations;
import bp.PAI_jwt.builder.TrackBuilder;
import bp.PAI_jwt.decorator.CategoryTrackDecorator;
import bp.PAI_jwt.decorator.TrackDecorator;
import bp.PAI_jwt.interpreter.Context;
import bp.PAI_jwt.interpreter.Expression;
import bp.PAI_jwt.interpreter.NameExpression;
import bp.PAI_jwt.model.Favorite;
import bp.PAI_jwt.model.Track;
import bp.PAI_jwt.model.User;
import bp.PAI_jwt.proxy.TrackProxy;
import bp.PAI_jwt.repository.FavoriteRepository;
import bp.PAI_jwt.repository.TrackRepository;  // Make sure to import the appropriate repository
import bp.PAI_jwt.repository.UserRepository;
import bp.PAI_jwt.factory.ResponseBody;
import bp.PAI_jwt.factory.ResponseFactory;
import bp.PAI_jwt.factory.ResponseFactoryImpl;
import bp.PAI_jwt.service.ExternalTrackService;
import bp.PAI_jwt.interpreter.Interpreter;
import bp.PAI_jwt.strategy.AdvancedFavoriteProcessingStrategy;
import bp.PAI_jwt.strategy.BasicFavoriteProcessingStrategy;
import bp.PAI_jwt.strategy.FavoriteProcessingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/api/tracks")
public class TrackController {

    @Autowired
    TrackRepository trackRepository;

    ResponseFactory responseFactory = new ResponseFactoryImpl();

    //Tydzień 3. Wzorzec Proxy  - podpięcie usługi używanej w proxy
    @Autowired
    ExternalTrackService externalTrackService; // Wstrzyknięcie ExternalTrackService

    //Tydzień 2. Wzorzec Bridge Początek - Użycie w endpointach  2 klas pochodnych
    // Endpoint to get basic info about a track using Bridge pattern
//    @GetMapping("/{id}/basicInfo")
//    public ResponseEntity<String> getBasicTrackInfo(@PathVariable("id") long id) {
//        // Find the track by id
//        Track track = trackRepository.findById(id).orElse(null);
//
//        // If the track is found, use Bridge pattern to get basic info
//        if (track != null) {
//            TrackOperations trackOperations = new BasicTrackOperations(track);
//            String basicInfo = trackOperations.getBasicInfo();
//            return new ResponseEntity<>(basicInfo, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("Track not found", HttpStatus.NOT_FOUND);
//        }
//    }

    // Tydzień 3 Wzorzec Proxy Początek
    @GetMapping("/{id}/basicInfo")
    public ResponseEntity<String> getBasicTrackInfo(@PathVariable("id") long id) {
        // Find the track by id
        Track track = trackRepository.findById(id).orElse(null);

        // If the track is found, use TrackProxy to get basic info
        if (track != null) {
            TrackOperations trackProxy = new TrackProxy(externalTrackService, id);
            String basicInfo = trackProxy.getBasicInfo();
            return new ResponseEntity<>(basicInfo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Track not found", HttpStatus.NOT_FOUND);
        }
    }
    // Tydzień 3 Wzorzec Proxy Koniec

    // Endpoint to get extended info about a track using Bridge pattern
    @GetMapping("/{id}/extendedInfo")
    public ResponseEntity<String> getExtendedTrackInfo(@PathVariable("id") long id) {
        // Find the track by id
        Track track = trackRepository.findById(id).orElse(null);

        // If the track is found, use Bridge pattern to get extended info
        if (track != null) {
            TrackOperations trackOperations = new ExtendedTrackOperations(track);
            String extendedInfo = trackOperations.getBasicInfo();
            return new ResponseEntity<>(extendedInfo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Track not found", HttpStatus.NOT_FOUND);
        }
    }
    //Tydzień 2. Wzorzec Bridge Koniec

    @GetMapping("")
    public ResponseEntity<List<TrackDTO>> getAllTracks(@RequestParam(required = false) String name) {
        try {
            List<Track> tracks = new ArrayList<>();

            if (name == null)
                trackRepository.findAll().forEach(tracks::add);
            else
                trackRepository.findByNameContaining(name).forEach(tracks::add);

            if (tracks.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            // Get the currently authenticated username
            String currentUsername = getCurrentUsername();

            // Convert Track entities to TrackDTOs with the 'favorite' field
            List<TrackDTO> trackDTOs = tracks.stream()
                    .map(track -> new TrackDTO(track, isFavoriteForUser(track, currentUsername)))
                    .collect(Collectors.toList());

            return new ResponseEntity<>(trackDTOs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getTrackCategory(@PathVariable("id") long id) {
        Optional<Track> trackData = trackRepository.findById(id);

        if (trackData.isPresent()) {
            Track track = trackData.get();
            //Tydzień 2. Wzorzec Decorator Początek - użycie w celu wyświetlenia kategori utworu.
            // Utwórz dekoratora kategorii i udekoruj obiekt Track
            TrackDecorator categoryDecorator = new CategoryTrackDecorator(track, "Rock");

            // Wyświetl informacje o kategorii
            String infoWithCategory = categoryDecorator.displayInfo();

            return new ResponseEntity<>(infoWithCategory, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Track not found", HttpStatus.NOT_FOUND);
        }
    }
//Tydzień 2. Wzorzec Decorator Koniec


    @PostMapping("")
    public ResponseEntity<ResponseBody> createTrack(@RequestBody Track track) {
        try {
            // Sprawdzamy, czy popularity można przekształcić na float
            try {
                if(track.getPopularity() != null) Float.parseFloat(track.getPopularity());
            } catch (NumberFormatException e) {
//                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
                return new ResponseEntity<>(
                        responseFactory.createErrorResponse("Zła wartość: Popularity", 400),
                        HttpStatus.BAD_REQUEST
                );
            }

            // Sprawdzamy, czy durationMs można przekształcić na float
            try {
                if(track.getDurationMs() != null) Float.parseFloat(track.getDurationMs());
            } catch (NumberFormatException e) {
//                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
                return new ResponseEntity<>(
                        responseFactory.createErrorResponse("Zła wartość: Duration", 400),
                        HttpStatus.BAD_REQUEST
                );
            }

            //Tydzień 1, Wzorzec Builder
            // Klasa TrackBuilder służy do budowania obiektów Track.
            // Umożliwia ustawianie poszczególnych pól tracka, takich jak nazwa, artysta, identyfikator Spotify, itd.
            Track newTrack = new TrackBuilder()
                    .setName(track.getName())
                    .setArtist(track.getArtists())
                    .setSpotifyId(track.getSpotifyId())
                    .setPreviewUrl(track.getPreviewUrl())
                    .setPopularity(track.getPopularity())
                    .setDurationMs(track.getDurationMs())
                    .setImage(track.getImage())
                    .build();
            //Koniec, Tydzień 1, Wzorzec Builder

            Track _track = trackRepository.save((Track) newTrack.clone());

            return new ResponseEntity<ResponseBody>(
                    //Tydzień 1, Wzorzec Factory
                    //Ten kod implementuje wzorzec projektowy fabryki dla generowania odpowiedzi API.
                    //Dostępne są dwa typy odpowiedzi: SuccessResponseBody (z danymi) i ErrorResponseBody (z komunikatem o błędzie).
                    responseFactory.createSuccessResponse(_track, "Dane zostały pomyślnie zapisane", 201),
                    //Koniec, Tydzień 1, Wzorzec Factory
                    HttpStatus.CREATED
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    responseFactory.createErrorResponse("Wystąpił błąd", 500),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Track> updateTrack(@PathVariable("id") long id, @RequestBody Track track) {
        // Sprawdzamy, czy popularity można przekształcić na float
        try {
            if(track.getPopularity() != null) Float.parseFloat(track.getPopularity());
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        // Sprawdzamy, czy durationMs można przekształcić na float
        try {
            if(track.getDurationMs() != null) Float.parseFloat(track.getDurationMs());
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        Optional<Track> trackData = trackRepository.findById(id);

        if (trackData.isPresent()) {
            Track _track = trackData.get();
            _track.setName(track.getName());
            _track.setArtists(track.getArtists());
            _track.setSpotifyId(track.getSpotifyId());
            _track.setPreviewUrl(track.getPreviewUrl());
            _track.setPopularity(track.getPopularity());
            _track.setDurationMs(track.getDurationMs());
            _track.setImage(track.getImage());
            return new ResponseEntity<>(trackRepository.save(_track), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTrack(@PathVariable("id") long id) {
        try {
            trackRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("")
    public ResponseEntity<HttpStatus> deleteAllTracks() {
        try {
            trackRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FavoriteRepository favoriteRepository;

    private boolean isFavoriteForUser(Track track, String username) {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findByUsername(username));
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return favoriteRepository.findByUserAndTrack(user, track).isPresent();
        }
        return false;
    }

    private String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    public static class TrackDTO {
        private final long id;
        private final String name;
        private final String artists;
        private final String spotifyId;
        private final String previewUrl;
        private final String popularity;
        private final String durationMs;
        private final String image;
        private final boolean favorite;

        public TrackDTO(Track track, boolean favorite) {
            this.id = track.getId();
            this.name = track.getName();
            this.artists = track.getArtists();
            this.spotifyId = track.getSpotifyId();
            this.previewUrl = track.getPreviewUrl();
            this.popularity = track.getPopularity();
            this.durationMs = track.getDurationMs();
            this.image = track.getImage();
            this.favorite = favorite;
        }

        public long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getArtists() {
            return artists;
        }

        public String getSpotifyId() {
            return spotifyId;
        }

        public String getPreviewUrl() {
            return previewUrl;
        }

        public String getPopularity() {
            return popularity;
        }

        public String getDurationMs() {
            return durationMs;
        }

        public String getImage() {
            return image;
        }

        public boolean isFavorite() {
            return favorite;
        }
    }

    // Tydzień 5, Wzorzec Interpreter
    @GetMapping("/favorites")
    public ResponseEntity<List<Track>> searchFavoritesByName(@RequestParam("name") String name) {
        List<Track> favorites = getFavoritesByName(name);
        return ResponseEntity.ok(favorites);
    }

    private List<Track> getFavoritesByName(String name) {
        List<Favorite> allFavorites = favoriteRepository.findAll();
        Context context = new Context(allFavorites);
        Expression nameExpression = new NameExpression(name);
        Interpreter interpreter = new Interpreter(nameExpression);
        return interpreter.interpret(context);
    }
    //Koniec, Tydzień 5, Wzorzec Interpreter



    // Tydzień 6, Wzorzec Strategy
    // Ten kod wprowadza elastyczność przetwarzania ulubionych elementów poprzez wzorzec Strategy. Metoda `setProcessingStrategy` pozwala na dynamiczną zmianę strategii przetwarzania na podstawie nazwy przekazanej jako parametr.
    // Natomiast metoda `processFavorite` wykorzystuje ustawioną strategię do przetworzenia ulubionego elementu, co umożliwia zmianę zachowania przetwarzania w czasie działania programu bez konieczności modyfikacji istniejącego kodu.
    private static FavoriteProcessingStrategy processingStrategy;

    public void setProcessingStrategy(FavoriteProcessingStrategy processingStrategy) {
        this.processingStrategy = processingStrategy;
    }

    @PostMapping("/favoritestrategy")
    public ResponseEntity<String> setProcessingStrategy(@RequestParam("strategy") String strategyName) {
        switch (strategyName) {
            case "basic":
                processingStrategy = new BasicFavoriteProcessingStrategy();
                break;
            case "advanced":
                processingStrategy = new AdvancedFavoriteProcessingStrategy();
                break;
            default:
                return ResponseEntity.badRequest().body("Nieprawidłowa nazwa strategii");
        }
        return ResponseEntity.ok("Strategia przetwarzania ulubionych utworów zmieniona na: " + strategyName);
    }

    @PostMapping("/processfavorite")
    public ResponseEntity<String> processFavorite(@RequestBody Favorite favorite) {
        // Przetwarzanie ulubionego utworu za pomocą ustawionej strategii
        processingStrategy.processFavorite(favorite);
        return ResponseEntity.ok("Ulubiony utwór przetworzony pomyślnie");
    }
    //Koniec, Tydzień 6, Wzorzec Strategy
}

