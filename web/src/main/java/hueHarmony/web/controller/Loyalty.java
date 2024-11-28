package hueHarmony.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loyalty")
public class Loyalty {

//    @GetMapping("/check-loyalty/{contact-no}")
//    @PreAuthorize("hasRole('ROLE_CACHIER')")
//    public ResponseEntity<Object> checkLoyalty(@PathVariable("contact-no") String contactNo) {
//        try{
//
//        }catch(Exception e){
//            return ResponseEntity.status(500).body("Internal Server Error");
//        }
//    }
//
//    @GetMapping("/add-to-loyalty/{contact-no}")
//    @PreAuthorize("hasRole('ROLE_CACHIER')")
//    public ResponseEntity<Object> addToLoyalty(@PathVariable("contact-no") String contactNo){
//        try{
//
//        }catch(Exception e){
//            return ResponseEntity.status(500).body("Internal Server Error");
//        }
//    }
//
//    @GetMapping("/check-my-loyalty")
//    @PreAuthorize("hasRole('ROLE_USER')")
//    public ResponseEntity<Object> addToLoyalty(){
//        try{
//
//        }catch(Exception e){
//            return ResponseEntity.status(500).body("Internal Server Error");
//        }
//    }
//
//    @PostMapping("/create-range")
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SALESMANAGER', 'ROLE_BACKOFFICE')")
//    public ResponseEntity<Object> addNewLoyaltyRange() {
//        try{
//
//        }catch(Exception e){
//            return ResponseEntity.status(500).body("Internal Server Error");
//        }
//    }
//
//    @PostMapping("/update-range")
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SALESMANAGER', 'ROLE_BACKOFFICE')")
//    public ResponseEntity<Object> updateLoyaltyRange() {
//        try{
//
//        }catch(Exception e){
//            return ResponseEntity.status(500).body("Internal Server Error");
//        }
//    }
//
//    @PostMapping("/update-discount-rate")
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SALESMANAGER', 'ROLE_BACKOFFICE')")
//    public ResponseEntity<Object> updateDDiscountRate() {
//        try{
//
//        }catch(Exception e){
//            return ResponseEntity.status(500).body("Internal Server Error");
//        }
//    }
//
//    @GetMapping("/view-ranges")
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SALESMANAGER', 'ROLE_BACKOFFICE', 'ROLE_CACHIER', 'ROLE_USER')")
//    public ResponseEntity<Object> viewLoyaltyRanges() {
//        try{
//
//        }catch(Exception e){
//            return ResponseEntity.status(500).body("Internal Server Error");
//        }
//    }
}
