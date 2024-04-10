package bp.PAI_jwt.memento;
import bp.PAI_jwt.model.Track;
import bp.PAI_jwt.model.User;

import java.util.Optional;

public class FavoriteMemento {
    private final Optional<User> user;
    private final Optional<Track> track;

    public FavoriteMemento(Optional<User> user, Optional<Track> track) {
        this.user = user;
        this.track = track;
    }

    public Optional<User> getUser() {
        return user;
    }

    public Optional<Track> getTrack() {
        return track;
    }
}