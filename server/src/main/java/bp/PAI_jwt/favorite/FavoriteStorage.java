package bp.PAI_jwt.favorite;

import bp.PAI_jwt.model.Favorite;

import java.util.List;

public interface FavoriteStorage {
    void addFavorite(Favorite favorite);
    void removeFavorite(Favorite favorite);
    List<Favorite> getFavorites();

    Favorite findById(Long id);
}