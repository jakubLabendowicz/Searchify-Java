package bp.PAI_jwt.strategy;

import bp.PAI_jwt.model.Favorite;

// Tydzień 6, Wzorzec Strategy
// Interfejs `FavoriteProcessingStrategy` definiuje metodę `processFavorite`, która jest odpowiedzialna za przetwarzanie ulubionego elementu.
// Kod implementuje wzorzec projektowy "Strategy", który umożliwia wybór algorytmu przetwarzania ulubionego elementu w trakcie działania programu.
public interface FavoriteProcessingStrategy {
    void processFavorite(Favorite favorite);
}
//Koniec, Tydzień 6, Wzorzec Strategy
