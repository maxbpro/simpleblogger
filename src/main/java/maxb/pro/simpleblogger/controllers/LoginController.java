package maxb.pro.simpleblogger.controllers;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import maxb.pro.simpleblogger.models.User;
import maxb.pro.simpleblogger.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.*;

@RestController
@RequestMapping
public class LoginController {

    static final String SECRET = "secretkey";
    static final String TOKEN_PREFIX = "Bearer";

    @Autowired
    private UsersRepository usersRepository;

    @PostConstruct
    private void init(){

//        usersRepository.deleteAll();
//
//        User user = new User();
//        user.setUsername("maxb2009");
//        user.setPassword("1234");
//        user.setFirstName("Max");
//        user.setLastName("Buyanow");
//        user.setEmail("maxbpro2009@gmail.com");
//        List<String> roles = new ArrayList<>();
//        roles.add("USER");
//        user.setRoles(roles);
//        usersRepository.save(user);
    }


    /**
     * This method is used for user registration. Note: user registration is not
     * require any authentication.
     *
     * @param appUser
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> registerUser(@RequestBody User appUser) {
        if (usersRepository.findByUsername(appUser.getUsername()) != null) {
            throw new RuntimeException("Username already exist");
        }
        List<String> roles = new ArrayList<>();
        roles.add("USER");
        appUser.setRoles(roles);

        usersRepository.save(appUser);

        Map<String, Object> tokenMap = new HashMap<>();
        if (appUser != null && appUser.getPassword().equals(appUser.getPassword())) {
            String token = Jwts.builder()
                    .setSubject(appUser.getUsername())
                    .claim("roles", appUser.getRoles()).setIssuedAt(new Date())
                    .signWith(SignatureAlgorithm.HS256, SECRET)
                    .compact();

            tokenMap.put("token", token);
            tokenMap.put("user", appUser);
            return new ResponseEntity<>(tokenMap, HttpStatus.OK);
        } else {
            tokenMap.put("token", null);
            return new ResponseEntity<>(tokenMap, HttpStatus.UNAUTHORIZED);
        }


    }


    /**
     * This method will return the logged user.
     *
     * @param principal
     * @return Principal java security principal object
     */
    @RequestMapping("/user")
    public User user(Principal principal) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedUsername = auth.getName();
        return usersRepository.findByUsername(loggedUsername);
    }

    /**
     * @param username
     * @param password
     * @param response
     * @return JSON contains token and user after success authentication.
     * @throws IOException
     */
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> login(@RequestParam String username, @RequestParam String password,
                                                     HttpServletResponse response) throws IOException {
        User appUser = usersRepository.findByUsername(username);
        Map<String, Object> tokenMap = new HashMap<>();
        if (appUser != null && appUser.getPassword().equals(password)) {
            String token = Jwts.builder()
                    .setSubject(username)
                    .claim("roles", appUser.getRoles()).setIssuedAt(new Date())
                    .signWith(SignatureAlgorithm.HS256, SECRET)
                    .compact();

            tokenMap.put("token", token);
            tokenMap.put("user", appUser);
            return new ResponseEntity<>(tokenMap, HttpStatus.OK);
        } else {
            tokenMap.put("token", null);
            return new ResponseEntity<>(tokenMap, HttpStatus.UNAUTHORIZED);
        }


    }

}
