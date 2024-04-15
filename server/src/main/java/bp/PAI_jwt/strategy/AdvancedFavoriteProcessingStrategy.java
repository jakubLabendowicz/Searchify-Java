package bp.PAI_jwt.strategy;

import bp.PAI_jwt.model.Favorite;

// Tydzień 6, Wzorzec Strategy
// Klasa `AdvancedFavoriteProcessingStrategy` implementuje interfejs `FavoriteProcessingStrategy`, dostarczając metodę `processFavorite`, która wypisuje identyfikator ulubionego elementu na konsoli w kontekście zaawansowanego przetwarzania.
// Ten kod reprezentuje kolejną konkretną strategię przetwarzania ulubionego elementu, umożliwiając elastyczne wybieranie algorytmów w czasie działania programu dzięki wzorcowi Strategy.
public class AdvancedFavoriteProcessingStrategy implements FavoriteProcessingStrategy {
    @Override
    public void processFavorite(Favorite favorite) {
        System.out.println("Przetwarzanie ulubionego utworu w sposób zaawansowany: " + favorite.getId());
    }
}
//Koniec, Tydzień 6, Wzorzec Strategy