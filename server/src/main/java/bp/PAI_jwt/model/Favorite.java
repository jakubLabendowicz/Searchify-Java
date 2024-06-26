package bp.PAI_jwt.model;
import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name = "favorites")
public class Favorite  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "track_id")
    private Track track;

    public Favorite() {
    }

    public Favorite(User user, Track track) {
        this.user = user;
        this.track = track;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Favorite favorite = (Favorite) o;
        return Objects.equals(user, favorite.user) &&
                Objects.equals(track, favorite.track);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, track);
    }
}
