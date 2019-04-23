package lk.royalBank.controller;

import lk.royalBank.dto.UserDTO;
import lk.royalBank.service.UserService;
import lk.royalBank.util.LoginDTO;
import lk.royalBank.util.LoginUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/{username}")
    public void addUser(@PathVariable("username") String userName, @RequestBody UserDTO userDTO){

        userService.addUser(userName,userDTO);

    }

    @PostMapping(path = "/login")
    public LoginUserDTO login(@RequestBody LoginDTO loginDTO){
        System.out.println(loginDTO.getUserName());
        return userService.login(loginDTO);
    }

    @GetMapping(value = "/{uname}")
    public UserDTO findByID(@PathVariable("uname") String uname){
        return  userService.findByID(uname);
    }

    @PutMapping(path = "/change")
    public void changePassword(@RequestParam("uname") String uname, @RequestParam("pass") String password){
        userService.changePassword(uname, password);
    }


}
