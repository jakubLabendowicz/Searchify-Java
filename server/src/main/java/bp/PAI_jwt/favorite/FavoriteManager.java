package bp.PAI_jwt.favorite;

import bp.PAI_jwt.model.Favorite;

public class FavoriteManager {
    public void printFavoriteDetails(FavoriteStorage storage) {
        for (Favorite favorite : storage.getFavorites()) {
            System.out.println(favorite);
        }
    }

    public void addFavorite(FavoriteStorage storage, Favorite favorite) {
        storage.addFavorite(favorite);
    }

    public void removeFavorite(FavoriteStorage storage, Favorite favorite) {
        storage.removeFavorite(favorite);
    }
}