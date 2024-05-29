package bp.PAI_jwt.controller;

import bp.PAI_jwt.favorite.FavoriteManager;
import bp.PAI_jwt.favorite.FavoriteStorage;
import bp.PAI_jwt.favorite.FavoriteTrackStorage;
import bp.PAI_jwt.model.Favorite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FavoriteController {
    private FavoriteStorage favoriteStorage = new FavoriteTrackStorage() {
        @Override
        public Favorite findById(Long id) {
            return null;
        }
    };

    @Autowired
    private FavoriteManager favoriteManager;

    @GetMapping("/favorites")
    public ResponseEntity<List<Favorite>> getAllFavorites() {
        List<Favorite> favorites = favoriteStorage.getFavorites();
        return ResponseEntity.ok(favorites);
    }

    @PostMapping("/favorites")
    public ResponseEntity<String> addFavorite(@RequestBody Favorite favorite) {
        favoriteManager.addFavorite(favoriteStorage, favorite);
        return ResponseEntity.ok("Favorite added successfully.");
    }

    @DeleteMapping("/favorites/{id}")
    public ResponseEntity<String> removeFavorite(@PathVariable Long id) {
        Favorite favoriteToRemove = favoriteStorage.findById(id);
        if (favoriteToRemove == null) {
            return ResponseEntity.notFound().build();
        }
        favoriteManager.removeFavorite(favoriteStorage, favoriteToRemove);
        return ResponseEntity.ok("Favorite removed successfully.");
    }

    @GetMapping("/favorites/details")
    public ResponseEntity<String> printFavoriteDetails() {
        favoriteManager.printFavoriteDetails(favoriteStorage);
        return ResponseEntity.ok("Favorite details printed successfully.");
    }
}