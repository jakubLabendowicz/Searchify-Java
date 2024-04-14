package bp.PAI_jwt.observer;

import bp.PAI_jwt.model.User;

public interface UserObserver {
    void onUserLogged(User user);
}
