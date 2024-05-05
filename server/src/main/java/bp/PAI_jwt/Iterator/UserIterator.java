package bp.PAI_jwt.Iterator;

import bp.PAI_jwt.model.User;
import java.util.Iterator;
import java.util.List;

// Tydzień 4 Wzorzec Iterator Początek - Definicja Iteratora dla klasy User

public class UserIterator implements Iterator<User> {
    private List<User> userList;
    private int currentIndex;

    public UserIterator(List<User> userList) {
        this.userList = userList;
        this.currentIndex = 0;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < userList.size();
    }

    @Override
    public User next() {
        return userList.get(currentIndex++);
    }
}