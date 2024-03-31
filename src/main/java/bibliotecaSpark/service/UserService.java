package bibliotecaSpark.service;

import bibliotecaSpark.model.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UserService {

    private static final Map<Integer, User> userDb = new HashMap<>();
    private static int id = 0;

    public static void add(User user){
        user.setUserId(++id);
        userDb.put(user.getUserId(), user);
    }

    public static void delete (int userId){
        userDb.remove(userId);
    }

    public static Collection<User> list() {
        return userDb.values();
    }

    public static User getById(int itemId) {
        return userDb.get(itemId);
    }
}
