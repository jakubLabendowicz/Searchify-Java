package bp.PAI_jwt.service;

import bp.PAI_jwt.builder.TrackBuilder;
import bp.PAI_jwt.decorator.ExtendedTrackInfoDecorator;
import bp.PAI_jwt.model.Track;
import bp.PAI_jwt.repository.TrackRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Tydzień 7 Początek. Zastosowanie zasady pojedynczej odpowiedzialnosci w klasie Track
public class TrackService {
    private final TrackRepository trackRepository;

    public TrackService(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    public List<Track> getAllTracks(String name) {
        List<Track> tracks = new ArrayList<>();

        if (name == null)
            trackRepository.findAll().forEach(tracks::add);
        else
            trackRepository.findByNameContaining(name).forEach(tracks::add);

        return tracks;
    }

    public Track getTrackById(long id) {
        Optional<Track> trackData = trackRepository.findById(id);
        return trackData.orElse(null);
    }

    // Metoda do udekorowania utworów
    public ExtendedTrackInfoDecorator decorateTrack(Track track) {
        // Tutaj możesz dodać logikę dekorowania utworu, np. dodając dodatkowe informacje
        return new ExtendedTrackInfoDecorator(track, "This is additional info");
    }

    public Track createTrack(Track track) {
        // Tworzenie nowego utworu
        Track newTrack = new TrackBuilder()
                .setName(track.getName())
                .setArtist(track.getArtists())
                .setSpotifyId(track.getSpotifyId())
                .setPreviewUrl(track.getPreviewUrl())
                .setPopularity(track.getPopularity())
                .setDurationMs(track.getDurationMs())
                .setImage(track.getImage())
                .build();

        // Zapisanie utworu do bazy danych
        return trackRepository.save(newTrack);
    }
}
// Tydzień 7 Koniec. Zastosowanie zasady pojedynczej odpowiedzialnosci w klasie Track