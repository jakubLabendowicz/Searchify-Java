package bp.PAI_jwt.tests;

import static org.junit.jupiter.api.Assertions.*;

import bp.PAI_jwt.model.Favorite;
import bp.PAI_jwt.model.Track;
import bp.PAI_jwt.model.User;
import org.junit.jupiter.api.Test;

class FavoriteTest {

    @Test
    void testFavoriteCreation() {
        User user = new User("John", "Doe", "johndoe", "password");
        Track track = new Track(1L, "Song Name", "Artist Name", "spotify_id_123", "preview_url", "high", "300000", "image_url");
        Favorite favorite = new Favorite(user, track);
        assertNotNull(favorite);
        assertEquals(user, favorite.getUser());
        assertEquals(track, favorite.getTrack());
    }

    @Test
    void testFavoriteIdSetting() {
        Favorite favorite = new Favorite();
        favorite.setId(1L);
        assertEquals(1L, favorite.getId());
    }

    @Test
    void testFavoriteUserSetting() {
        User user = new User("John", "Doe", "johndoe", "password");
        Favorite favorite = new Favorite();
        favorite.setUser(user);
        assertEquals(user, favorite.getUser());
    }

    @Test
    void testFavoriteTrackSetting() {
        Track track = new Track(1L, "Song Name", "Artist Name", "spotify_id_123", "preview_url", "high", "300000", "image_url");
        Favorite favorite = new Favorite();
        favorite.setTrack(track);
        assertEquals(track, favorite.getTrack());
    }

    @Test
    void testFavoriteEquality() {
        User user1 = new User("John", "Doe", "johndoe", "password");
        User user2 = new User("Jane", "Doe", "janedoe", "password");
        Track track1 = new Track(1L, "Song Name", "Artist Name", "spotify_id_123", "preview_url", "high", "300000", "image_url");
        Track track2 = new Track(1L, "Song Name", "Artist Name", "spotify_id_123", "preview_url", "high", "300000", "image_url");

        Favorite favorite1 = new Favorite(user1, track1);
        Favorite favorite2 = new Favorite(user1, track1);
        Favorite favorite3 = new Favorite(user2, track1);
        Favorite favorite4 = new Favorite(user1, track2);

        assertEquals(favorite1, favorite2); // Should be equal because they have the same user and track
        assertNotEquals(favorite1, favorite3); // Should not be equal because they have different users
        assertNotEquals(favorite1, favorite4); // Should not be equal because they have different tracks
    }
}
