package bp.PAI_jwt.segregatedinterfaces;


import bp.PAI_jwt.model.Favorite;
import bp.PAI_jwt.model.Track;
import bp.PAI_jwt.model.User;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {
    void save(T entity);
    T findById(Long id);
    void deleteById(Long id);
}