package maxb.pro.simpleblogger.controllers;

import maxb.pro.simpleblogger.models.User;
import maxb.pro.simpleblogger.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UsersController {


    @Autowired
    private UserService userService;


    @GetMapping("/users")
    public List<User> allUsers() {
        return userService.getUsers();
    }

    @PostMapping("/users")
    public User createUser(@Valid @RequestBody User user) {
        return userService.saveUser(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") String id, @RequestBody User user) {

        User oldUser = userService.findUser(id);
        if (oldUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        oldUser.setFirstName (user.getFirstName());
        oldUser.setLastName (user.getLastName());
        User updateUser = userService.saveUser(oldUser);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") String id) {
        userService.deleteUser(id);
        return new ResponseEntity<>("User has been deleted!", HttpStatus.OK);
    }


}
