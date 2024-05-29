package bp.PAI_jwt.segregatedinterfaces;

import bp.PAI_jwt.model.Favorite;
import bp.PAI_jwt.model.Track;
import bp.PAI_jwt.model.User;

import java.util.List;

public interface FavoriteCrudRepository extends CrudRepository<Favorite> {
    void save(Favorite favorite);

    Favorite findById(Long id);

    void deleteById(Long id);

    long count();

    List<Favorite> findByName(String name);
}


