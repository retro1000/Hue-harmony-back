package hueHarmony.web.controller;

import hueHarmony.web.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class User {

    @GetMapping("/view/{userId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_INVENTORYMANAGER', 'ROLE_BACKOFFICE', 'ROLE_SALESMANAGER', 'ROLE_USER', 'ROLE_CACHIER')")
    public ResponseEntity<Object> viewProfile(@PathVariable("userId") int userId) {
        try{

        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    @GetMapping("/profile/{userId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_INVENTORYMANAGER', 'ROLE_BACKOFFICE', 'ROLE_SALESMANAGER', 'ROLE_USER', 'ROLE_CACHIER')")
    public ResponseEntity<Object> viewProfile(@PathVariable("userId") int userId) {
        try{

        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    @GetMapping("/filter")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> filterUser(@Validated(FilterUserDto.whenOrganization.class) @ModelAttribute FilterUserDto request) {
        try{

        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> createUser(
            @Validated(UserDto.onCreation.class) @RequestBody UserDto userDto,
            BindingResult bindingResult
    ) {
        try{

        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    @PostMapping("/assign-role")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> assignRole(
            @Validated(UserDto.onUpdateRole.class) @RequestBody UserDto userDto,
            BindingResult bindingResult
    ) {
        try{

        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    @PostMapping("/update")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_INVENTORYMANAGER', 'ROLE_BACKOFFICE', 'ROLE_SALESMANAGER', 'ROLE_USER', 'ROLE_CACHIER')")
    public ResponseEntity<Object> updateUser(
            @Validated(UserDto.onUpdate.class) @ModelAttribute UserDto userDto,
            BindingResult bindingResult
    ) {
        try{

        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    @PostMapping("/signup")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Object> signup(
            @Validated(UserDto.onSignup.class) @RequestBody UserDto userDto,
            BindingResult bindingResult
    ) {
        try{

        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    @PostMapping("/change-password")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_INVENTORYMANAGER', 'ROLE_BACKOFFICE', 'ROLE_SALESMANAGER', 'ROLE_USER', 'ROLE_CACHIER')")
    public ResponseEntity<Object> changePassword(
            @Validated(ChangePasswordDto.onCheck.class) @RequestBody ChangePasswordDto changePasswordDto,
            BindingResult bindingResult
    ) {
        try{

        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

//    @DeleteMapping("/delete")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public ResponseEntity<Object> deleteUser(
//            @Validated(UserDto.class) @RequestBody UserDto userDto,
//            BindingResult bindingResult
//    ) {
//        try{
//
//        }catch(Exception e){
//            return ResponseEntity.status(500).body("Internal Server Error");
//        }
//    }

    @PostMapping("/enable")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> enableDisableUser(
            @Validated(UserDto.onUpdateStatus.class) @RequestBody UserDto userDto,
            BindingResult bindingResult
    ) {
        try{

        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }
}
