package bp.PAI_jwt.interpreter;

import bp.PAI_jwt.model.Favorite;
import bp.PAI_jwt.model.Track;

import java.util.ArrayList;
import java.util.List;

// Tydzień 5, Wzorzec Interpreter
public class Context {
    private List<Favorite> favorites;

    public Context(List<Favorite> favorites) {
        this.favorites = favorites;
    }

    public List<Track> getFavoritesByName(String name) {
        List<Track> result = new ArrayList<>();
        for (Favorite favorite : favorites) {
            Track track = favorite.getTrack();
            if (track.getName().equalsIgnoreCase(name)) {
                result.add(track);
            }
        }
        return result;
    }
}
//Koniec, Tydzień 5, Wzorzec Interpreter