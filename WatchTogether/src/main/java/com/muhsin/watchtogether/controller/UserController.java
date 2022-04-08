package com.muhsin.watchtogether.controller;

import com.muhsin.watchtogether.model.User;
import com.muhsin.watchtogether.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("user/")
@CrossOrigin
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("allusers")
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(this.userRepository.getAllUsers(), HttpStatus.OK);
    }
    @GetMapping({"{userId}"})
    public ResponseEntity<User> getUser(@PathVariable(value = "userId") Integer userId){
        return new ResponseEntity<>(this.userRepository.findUserById(userId), HttpStatus.OK);
    }

    @GetMapping("allusers2")
    public ResponseEntity<List<User>> getAllUsers2(){
        return new ResponseEntity<>(this.userRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("adduser")
    public ResponseEntity<?> addUser(@RequestBody User newUser){
        User u = userRepository.save(newUser);
        return new ResponseEntity<>(u.toString(), HttpStatus.OK);
    }

    @PutMapping("putuser/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User oldUser, @PathVariable(value = "id") Integer id){
        User user = userRepository.findUserById(id);
        if (user!= null){
            user.setFirstName(oldUser.getFirstName());
            user.setLastName(oldUser.getLastName());
            User updatedUser = userRepository.save(user);
            return new ResponseEntity<>(updatedUser, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("deleteuser/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Integer id){
        User user = userRepository.findUserById(id);
        if (user != null){
            userRepository.delete(user);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
