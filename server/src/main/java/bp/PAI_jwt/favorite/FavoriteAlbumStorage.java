package bp.PAI_jwt.favorite;

import bp.PAI_jwt.model.Favorite;

import java.util.ArrayList;
import java.util.List;

public abstract class FavoriteAlbumStorage implements FavoriteStorage {
    private List<Favorite> favorites = new ArrayList<>();

    @Override
    public void addFavorite(Favorite favorite) {
        favorites.add(favorite);
    }

    @Override
    public void removeFavorite(Favorite favorite) {
        favorites.remove(favorite);
    }

    @Override
    public List<Favorite> getFavorites() {
        return favorites;
    }
}