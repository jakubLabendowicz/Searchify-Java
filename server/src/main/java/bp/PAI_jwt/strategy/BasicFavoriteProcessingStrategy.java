package bp.PAI_jwt.strategy;

import bp.PAI_jwt.model.Favorite;

public class BasicFavoriteProcessingStrategy implements FavoriteProcessingStrategy {
    @Override
    public void processFavorite(Favorite favorite) {
        System.out.println("Przetwarzanie ulubionego utworu w sposób podstawowy: " + favorite.getId());
    }
}