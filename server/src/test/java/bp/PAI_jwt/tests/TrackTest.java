package bp.PAI_jwt.tests;

import static org.junit.jupiter.api.Assertions.*;

import bp.PAI_jwt.model.Track;
import bp.PAI_jwt.visitor.Visitor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class TrackTest {

    @Test
    void testTrackCreationWithAllFields() {
        Track track = new Track(1L, "Song Name", "Artist Name", "spotify_id_123", "preview_url", "high", "300000", "image_url");
        assertNotNull(track);
        assertEquals(1L, track.getId());
        assertEquals("Song Name", track.getName());
        assertEquals("Artist Name", track.getArtists());
        assertEquals("spotify_id_123", track.getSpotifyId());
        assertEquals("preview_url", track.getPreviewUrl());
        assertEquals("high", track.getPopularity());
        assertEquals("300000", track.getDurationMs());
        assertEquals("image_url", track.getImage());
    }

    @Test
    void testTrackCreationWithOnlyNameAndArtists() {
        Track track = new Track("Song Name", "Artist Name", null, null, null, null, null);
        assertNotNull(track);
       // assertNull(track.getId());
        assertEquals("Song Name", track.getName());
        assertEquals("Artist Name", track.getArtists());
        assertNull(track.getSpotifyId());
        assertNull(track.getPreviewUrl());
        assertNull(track.getPopularity());
        assertNull(track.getDurationMs());
        assertNull(track.getImage());
    }

    @Test
    void testTrackClone() {
        Track originalTrack = new Track(1L, "Song Name", "Artist Name", "spotify_id_123", "preview_url", "high", "300000", "image_url");
        Track clonedTrack = (Track) originalTrack.clone();
        assertNotSame(originalTrack, clonedTrack);
        assertEquals(originalTrack.getId(), clonedTrack.getId());
        assertEquals(originalTrack.getName(), clonedTrack.getName());
        assertEquals(originalTrack.getArtists(), clonedTrack.getArtists());
        assertEquals(originalTrack.getSpotifyId(), clonedTrack.getSpotifyId());
        assertEquals(originalTrack.getPreviewUrl(), clonedTrack.getPreviewUrl());
        assertEquals(originalTrack.getPopularity(), clonedTrack.getPopularity());
        assertEquals(originalTrack.getDurationMs(), clonedTrack.getDurationMs());
        assertEquals(originalTrack.getImage(), clonedTrack.getImage());
    }

    @Test
    void testTrackAcceptVisitor() {
        // Define a mock visitor
        Visitor mockVisitor = Mockito.mock(Visitor.class);

        Track track = new Track(1L, "Song Name", "Artist Name", "spotify_id_123", "preview_url", "high", "300000", "image_url");
        track.accept(mockVisitor);

        // Verify that the visit method of the mock visitor was called with the track object
        Mockito.verify(mockVisitor, Mockito.times(1)).visit(track);
    }

    @Test
    void testTrackAcceptVisitorWithNullVisitor() {
        Track track = new Track(1L, "Song Name", "Artist Name", "spotify_id_123", "preview_url", "high", "300000", "image_url");
        assertThrows(NullPointerException.class, () -> track.accept(null));
    }
}
