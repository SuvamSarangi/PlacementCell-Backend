package com.example.placementcell.Controller;
import com.example.placementcell.Payload.ApiResponce;
import com.example.placementcell.Payload.UserDto;
import com.example.placementcell.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/")
    private ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){

        UserDto createUserDto = this.userService.createUser(userDto);
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);

    }
    // Get user
    @GetMapping("/all")
    public ResponseEntity<List<UserDto>>getAllUser(){

        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    // Get user by Id
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto>getUserById(@PathVariable Long userId){

        return ResponseEntity.ok(this.userService.getUserById(userId));
    }

    //Put -- Update User
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{userId}")
    private ResponseEntity<UserDto>updateUser(@RequestBody UserDto userDto,@PathVariable Long userId){
        UserDto updateUserDto = this.userService.updateUser(userId , userDto);
        return  new ResponseEntity<>(updateUserDto,HttpStatus.OK);

    }

    // Delete User

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponce>deleteUser(@PathVariable Long userId){
        this.userService.deleteUser(userId);
        return new  ResponseEntity<ApiResponce>(new ApiResponce("User Deleted Successfully",true),HttpStatus.OK);
    }

}
