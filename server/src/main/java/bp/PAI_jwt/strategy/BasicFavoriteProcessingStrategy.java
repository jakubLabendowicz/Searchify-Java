package bp.PAI_jwt.strategy;

import bp.PAI_jwt.model.Favorite;

// Tydzień 6, Wzorzec Strategy
// Klasa `BasicFavoriteProcessingStrategy` implementuje interfejs `FavoriteProcessingStrategy`, definiując metodę `processFavorite`, która wypisuje identyfikator ulubionego elementu na konsoli.
// Kod ten reprezentuje konkretne zachowanie przetwarzania ulubionego elementu i stanowi jedną z możliwych strategii w kontekście wzorca Strategy.
public class BasicFavoriteProcessingStrategy implements FavoriteProcessingStrategy {
    @Override
    public Object processFavorite(Favorite favorite) {
        System.out.println("Przetwarzanie ulubionego utworu w sposób podstawowy: " + favorite.getId());
        return favorite.getUser();
    }
}
//Koniec, Tydzień 6, Wzorzec Strategy
