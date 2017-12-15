package maxb.pro.simpleblogger.services.impl;

import maxb.pro.simpleblogger.models.User;
import maxb.pro.simpleblogger.repository.UsersRepository;
import maxb.pro.simpleblogger.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepository usersRepository;

    public List<User> getUsers(){
        return usersRepository.findAll();
    }


    @Override
    public User saveUser(User user) {
        return usersRepository.save(user);
    }

    @Override
    public void deleteUser(User user) {
        usersRepository.delete(user);
    }

    @Override
    public void deleteUser(String id) {
        usersRepository.delete(id);
    }

    @Override
    public User findUser(String id) {
        return usersRepository.findOne(id);
    }
}
