package bp.PAI_jwt.playlist;

import bp.PAI_jwt.model.User;

import java.util.ArrayList;
import java.util.List;

public class CollaborativePlaylist extends Playlist {
    private List<User> collaborators;

    public CollaborativePlaylist(String name) {
        super(name);
        this.collaborators = new ArrayList<>();
    }

    public void addCollaborator(User user) {
        collaborators.add(user);
    }

    public void removeCollaborator(User user) {
        collaborators.remove(user);
    }

    public List<User> getCollaborators() {
        return collaborators;
    }

    @Override
    public String toString() {
        return "CollaborativePlaylist{" +
                "name='" + getName() + '\'' +
                ", tracks=" + getTracks() +
                ", collaborators=" + collaborators +
                '}';
    }
}