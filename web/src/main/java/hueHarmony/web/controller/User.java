package hueHarmony.web.controller;

import hueHarmony.web.dto.FilterOrderDto;
import hueHarmony.web.dto.FilterUserDto;
import hueHarmony.web.dto.FilterVariationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public ResponseEntity<Object> createUser() {
        try{

        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    @PostMapping("/assign-role")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> assignRole() {
        try{

        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    @PostMapping("/update")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_INVENTORYMANAGER', 'ROLE_BACKOFFICE', 'ROLE_SALESMANAGER', 'ROLE_USER', 'ROLE_CACHIER')")
    public ResponseEntity<Object> updateUser() {
        try{

        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    @PostMapping("/signup")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Object> signup() {
        try{

        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    @PostMapping("/change-password")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_INVENTORYMANAGER', 'ROLE_BACKOFFICE', 'ROLE_SALESMANAGER', 'ROLE_USER', 'ROLE_CACHIER')")
    public ResponseEntity<Object> changePassword() {
        try{

        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> deleteUser() {
        try{

        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    @PostMapping("/enable")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> enableDisableUser() {
        try{

        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }
}
