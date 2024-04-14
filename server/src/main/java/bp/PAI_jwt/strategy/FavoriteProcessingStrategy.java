package bp.PAI_jwt.strategy;

import bp.PAI_jwt.model.Favorite;

public interface FavoriteProcessingStrategy {
    void processFavorite(Favorite favorite);
}

