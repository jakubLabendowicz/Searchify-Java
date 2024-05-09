package bp.PAI_jwt.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import bp.PAI_jwt.bridge.ExtendedTrackOperations;
import bp.PAI_jwt.bridge.TrackOperations;
import bp.PAI_jwt.builder.TrackBuilder;
import bp.PAI_jwt.command.BasicTrackInfoCommand;
import bp.PAI_jwt.command.TrackCommand;
import bp.PAI_jwt.decorator.CategoryTrackDecorator;
import bp.PAI_jwt.decorator.TrackDecorator;
import bp.PAI_jwt.functionalInterfaces.NumberValidator;
import bp.PAI_jwt.functionalInterfaces.TrackDeleter;
import bp.PAI_jwt.functionalInterfaces.UserCreator;
import bp.PAI_jwt.interpreter.Context;
import bp.PAI_jwt.interpreter.Expression;
import bp.PAI_jwt.interpreter.NameExpression;
import bp.PAI_jwt.model.Favorite;
import bp.PAI_jwt.model.Track;
import bp.PAI_jwt.model.User;
import bp.PAI_jwt.repository.FavoriteRepository;
import bp.PAI_jwt.repository.TrackRepository;
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


    // Tydzień 4 Wzorzec Command Początek - Implentacja wzorca w endpoincie, W metodzie getBasicTrackInfo kontrolera
    // TrackController tworzymy instancję klasy BasicTrackInfoCommand i wykonujemy ją. W ten sposób oddzielamy żądanie
    // od rzeczywistej operacji do wykonania, co jest zgodne z wzorcem Command.
    @GetMapping("/{id}/basicInfo")
    public ResponseEntity<String> getBasicTrackInfo(@PathVariable("id") long id) {
        // Find the track by id
        Track track = trackRepository.findById(id).orElse(null);

        // If the track is found, execute the command to get basic info
        if (track != null) {
            TrackCommand command = new BasicTrackInfoCommand(externalTrackService, id);
            String basicInfo = command.execute();
            return new ResponseEntity<>(basicInfo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Track not found", HttpStatus.NOT_FOUND);
        }
    }
    //Tydzień 4 Wzorzec Command Koniec

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

    //Tydzień 9, 2. Dostosuj długości metod w programie, żeby nie miały więcej niż 20 linii
    //Tydzień 9, 3. Dostosuj funkcje tak by spełniały tylko jedną rolę
    @GetMapping("")
    public ResponseEntity<List<TrackDTO>> getAllTracks(@RequestParam(required = false) String name) {
        try {
            List<Track> tracks = retrieveTracks(name);
            if (tracks.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            String currentUsername = getCurrentUsername();
            List<TrackDTO> trackDTOs = convertToTrackDTOs(tracks, currentUsername);

            return new ResponseEntity<>(trackDTOs, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve tracks", e);
        }
    }

    private List<Track> retrieveTracks(String name) {
        List<Track> tracks = new ArrayList<>();
        if (name == null)
            trackRepository.findAll().forEach(tracks::add);
        else
            trackRepository.findByNameContaining(name).forEach(tracks::add);
        return tracks;
    }

    private List<TrackDTO> convertToTrackDTOs(List<Track> tracks, String currentUsername) {
        return tracks.stream()
                .map(track -> new TrackDTO(track, isFavoriteForUser(track, currentUsername)))
                .collect(Collectors.toList());
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

            //Tydzień 2. Wzorzec Decorator Koniec

            return new ResponseEntity<>(infoWithCategory, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Track not found", HttpStatus.NOT_FOUND);
        }
    }

    //Tydzień 9, 2. Dostosuj długości metod w programie, żeby nie miały więcej niż 20 linii
    //Tydzień 9, 3. Dostosuj funkcje tak by spełniały tylko jedną rolę
    @PostMapping("")
    public ResponseEntity<ResponseBody> createTrack(@RequestBody Track incomingTrack) {
        try {
            validateTrackPopularityAndDuration(incomingTrack);

            Track newTrack = buildTrackFromRequest(incomingTrack);
            Track savedTrack = saveTrackToRepository(newTrack);

            ResponseBody successResponse = createSuccessResponse(savedTrack);
            return ResponseEntity.status(HttpStatus.CREATED).body(successResponse);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid popularity or duration format", e);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create track", e);
        }
    }

    private void validateTrackPopularityAndDuration(Track track) {
        validateNumericField(track.getPopularity(), "Popularity");
        validateNumericField(track.getDurationMs(), "Duration");
    }

    private void validateNumericField(String value, String fieldName) {
        if (value != null) {
            try {
                Float.parseFloat(value);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid value for " + fieldName, e);
            }
        }
    }

    private Track buildTrackFromRequest(Track incomingTrack) {
        //Tydzień 1, Wzorzec Builder
        // Klasa TrackBuilder służy do budowania obiektów Track.
        // Umożliwia ustawianie poszczególnych pól tracka, takich jak nazwa, artysta, identyfikator Spotify, itd.
        return new TrackBuilder()
                .setName(incomingTrack.getName())
                .setArtist(incomingTrack.getArtists())
                .setSpotifyId(incomingTrack.getSpotifyId())
                .setPreviewUrl(incomingTrack.getPreviewUrl())
                .setPopularity(incomingTrack.getPopularity())
                .setDurationMs(incomingTrack.getDurationMs())
                .setImage(incomingTrack.getImage())
                .build();
        //Koniec, Tydzień 1, Wzorzec Builder
    }

    private Track saveTrackToRepository(Track track) {
        return trackRepository.save((Track) track.clone());
    }

    private ResponseBody createSuccessResponse(Track track) {
        //Tydzień 1, Wzorzec Factory
        //Ten kod implementuje wzorzec projektowy fabryki dla generowania odpowiedzi API.
        //Dostępne są dwa typy odpowiedzi: SuccessResponseBody (z danymi) i ErrorResponseBody (z komunikatem o błędzie).
        return responseFactory.createSuccessResponse(
                track,
                "Track successfully created",
                HttpStatus.CREATED.value()
        );
        //Koniec, Tydzień 1, Wzorzec Factory
    }

    //Tydzień 9, 2. Dostosuj długości metod w programie, żeby nie miały więcej niż 20 linii
    //Tydzień 9, 3. Dostosuj funkcje tak by spełniały tylko jedną rolę
    @PutMapping("/{id}")
    public ResponseEntity<Track> updateTrack(@PathVariable("id") long id, @RequestBody Track track) {
        try {
            // Tydzień 10, utwórz 3 interfejsy funkcyjne, wykonaj ich implementacje i napisz kod używający instancji tych interfejsów poprzez zastosowanie wyrażenia lambda
            NumberValidator popularityValidator = value -> {
                if (value != null)
                    Float.parseFloat(value);
            };

            NumberValidator durationValidator = value -> {
                if (value != null)
                    Float.parseFloat(value);
            };

            validateAttribute(track.getPopularity(), popularityValidator);
            validateAttribute(track.getDurationMs(), durationValidator);
            //Koniec, Tydzień 10, utwórz 3 interfejsy funkcyjne, wykonaj ich implementacje i napisz kod używający instancji tych interfejsów poprzez zastosowanie wyrażenia lambda

            Optional<Track> trackData = trackRepository.findById(id);

            if (trackData.isPresent()) {
                Track updatedTrack = updateTrackData(trackData.get(), track);
                return new ResponseEntity<>(trackRepository.save(updatedTrack), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (NumberFormatException e) {
            return handleNumberFormatException();
        } catch (Exception e) {
            return handleInternalServerError();
        }
    }

    private void validateAttribute(String value, NumberValidator validator) {
        try {
            validator.validate(value);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid number format: " + value);
        }
    }

    private Track updateTrackData(Track existingTrack, Track newTrackData) {
        existingTrack.setName(newTrackData.getName());
        existingTrack.setArtists(newTrackData.getArtists());
        existingTrack.setSpotifyId(newTrackData.getSpotifyId());
        existingTrack.setPreviewUrl(newTrackData.getPreviewUrl());
        existingTrack.setPopularity(newTrackData.getPopularity());
        existingTrack.setDurationMs(newTrackData.getDurationMs());
        existingTrack.setImage(newTrackData.getImage());
        return existingTrack;
    }

    private ResponseEntity<Track> handleNumberFormatException() {
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Track> handleInternalServerError() {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void validatePopularity(Track track) {
        if (track.getPopularity() != null)
            Float.parseFloat(track.getPopularity());
    }

    private void validateDurationMs(Track track) {
        if (track.getDurationMs() != null)
            Float.parseFloat(track.getDurationMs());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTrack(@PathVariable("id") long id) {
        try {
            // Tydzień 10, utwórz 3 interfejsy funkcyjne, wykonaj ich implementacje i napisz kod używający instancji tych interfejsów poprzez zastosowanie wyrażenia lambda
            TrackDeleter trackDeleter = (trackId) -> trackRepository.deleteById(trackId);
            trackDeleter.deleteTrack(id);
            //Koniec, Tydzień 10, utwórz 3 interfejsy funkcyjne, wykonaj ich implementacje i napisz kod używający instancji tych interfejsów poprzez zastosowanie wyrażenia lambda

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
        Object object = processingStrategy.processFavorite(favorite);
        return ResponseEntity.ok(object.toString());
    }
    //Koniec, Tydzień 6, Wzorzec Strategy
}


