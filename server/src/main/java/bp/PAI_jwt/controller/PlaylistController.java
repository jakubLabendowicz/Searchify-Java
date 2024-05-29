package bp.PAI_jwt.controller;

import bp.PAI_jwt.model.Track;
import bp.PAI_jwt.playlist.Playlist;
import bp.PAI_jwt.playlist.PlaylistManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PlaylistController {
    /*
    private final PlaylistStorage playlistStorage;

    @Autowired
    public PlaylistController(PlaylistStorage playlistStorage) {
        this.playlistStorage = playlistStorage;
    }

    @Autowired
    private PlaylistManager playlistManager;

    @GetMapping("/playlists/{playlistId}")
    public ResponseEntity<Playlist> getPlaylistById(@PathVariable Long playlistId) {
        Playlist playlist = playlistStorage.findById(playlistId);
        if (playlist == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(playlist);
    }

    @PostMapping("/playlists/{playlistId}/tracks")
    public ResponseEntity<String> addTrackToPlaylist(@PathVariable Long playlistId, @RequestBody Track track) {
        Playlist playlist = playlistStorage.findById(playlistId);
        if (playlist == null) {
            return ResponseEntity.notFound().build();
        }
        playlistManager.addTrackToPlaylist(playlist, track);
        return ResponseEntity.ok("Track added to playlist successfully.");
    }

    @GetMapping("/playlists/{playlistId}/details")
    public ResponseEntity<String> printPlaylistDetails(@PathVariable Long playlistId) {
        Playlist playlist = playlistStorage.findById(playlistId);
        if (playlist == null) {
            return ResponseEntity.notFound().build();
        }
        playlistManager.printPlaylistDetails(playlist);
        return ResponseEntity.ok("Playlist details printed successfully.");
    }

     */
}