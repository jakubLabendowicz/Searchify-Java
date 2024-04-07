package bp.PAI_jwt.mediator;

import bp.PAI_jwt.model.Favorite;
import org.springframework.http.ResponseEntity;

// Tydzień 5, Wzorzec Mediator
public interface FavoriteMediator {
    ResponseEntity<Favorite> addSelfFavorite(long userId, long trackId);
}
//Koniec, Tydzień 5, Wzorzec Mediator

