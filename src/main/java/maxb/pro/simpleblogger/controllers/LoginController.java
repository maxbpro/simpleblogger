package maxb.pro.simpleblogger.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class LoginController {

    @RequestMapping(value = "/login")
    private void login(){

    }

    @RequestMapping(value = "/logout")
    private void logout(){

    }
}
