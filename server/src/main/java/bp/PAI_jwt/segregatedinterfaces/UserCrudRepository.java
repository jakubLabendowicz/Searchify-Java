package bp.PAI_jwt.segregatedinterfaces;

import bp.PAI_jwt.model.User;

import java.util.List;

public interface UserCrudRepository extends CrudRepository<User> {
    void save(User user);

    User findById(Long id);

    void deleteById(Long id);

    long count();

    List<User> findByFirstName(String firstName);
}