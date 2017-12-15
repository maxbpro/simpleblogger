package maxb.pro.simpleblogger.services;

import maxb.pro.simpleblogger.models.User;

import java.util.List;

public interface UserService {

    List<User> getUsers();

    User saveUser(User user);

    void deleteUser(User user);

    void deleteUser(String id);

    User findUser(String id);
}
