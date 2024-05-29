package bp.PAI_jwt.tests;

import bp.PAI_jwt.model.Track;
import bp.PAI_jwt.playlist.Playlist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlaylistTest {
    private Playlist playlist;
    private Track track1;
    private Track track2;

    @BeforeEach
    void setUp() {
        playlist = new Playlist("My Playlist");
        track1 = new Track(1L, "Song 1", "Artist 1", "spotify_id_1", "preview_url_1", "high", "300000", "image_url_1");
        track2 = new Track(2L, "Song 2", "Artist 2", "spotify_id_2", "preview_url_2", "high", "300000", "image_url_2");
    }

    @Test
    void testAddTrack() {
        playlist.addTrack(track1);
        List<Track> tracks = playlist.getTracks();
        assertEquals(1, tracks.size());
        assertTrue(tracks.contains(track1));
    }

    @Test
    void testRemoveTrack() {
        playlist.addTrack(track1);
        playlist.removeTrack(track1);
        List<Track> tracks = playlist.getTracks();
        assertEquals(0, tracks.size());
        assertFalse(tracks.contains(track1));
    }

    @Test
    void testGetTracks() {
        playlist.addTrack(track1);
        playlist.addTrack(track2);
        List<Track> tracks = playlist.getTracks();
        assertEquals(2, tracks.size());
        assertTrue(tracks.contains(track1));
        assertTrue(tracks.contains(track2));
    }

    @Test
    void testGetName() {
        assertEquals("My Playlist", playlist.getName());
    }

    @Test
    void testAddDuplicateTrack() {
        playlist.addTrack(track1);
        playlist.addTrack(track1);
        List<Track> tracks = playlist.getTracks();
        assertEquals(2, tracks.size());
    }
}