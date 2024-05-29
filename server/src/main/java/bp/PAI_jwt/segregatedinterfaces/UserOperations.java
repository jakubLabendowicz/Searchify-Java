package bp.PAI_jwt.segregatedinterfaces;

import bp.PAI_jwt.model.User;

public interface UserOperations {
    User findByUsername(String username);
}