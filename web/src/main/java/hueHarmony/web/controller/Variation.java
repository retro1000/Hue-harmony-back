package hueHarmony.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/variation")
public class Variation {

    @GetMapping("/view/{variationId}")
    @PreAuthorize("hasAnyRole('ROLE_INVENTORYMANAGER', 'ROLE_BACKOFFICE')")
    public ResponseEntity<Object> viewVariation(@PathVariable("variationId") int variationId) {
        try{

        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    @GetMapping("/filter")
    @PreAuthorize("hasAnyRole('ROLE_INVENTORYMANAGER', 'ROLE_BACKOFFICE')")
    public ResponseEntity<Object> filterVariation(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "select", defaultValue = "all") String select,
            @RequestParam(value = "brand", required = false) List<String> brands,
            @RequestParam(value = "color", required = false) List<String> color,
            @RequestParam(value = "size", required = false) List<String> size,
            @RequestParam(value = "price", required = false) List<String> price,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "10") int limit,
            @RequestParam(value = "sort", defaultValue = "id,asc") String sort
    ) {
        try{

        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_BACKOFFICE')")
    public ResponseEntity<Object> createVariation() {
        try{

        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('ROLE_BACKOFFICE')")
    public ResponseEntity<Object> updateVariation() {
        try{

        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ROLE_BACKOFFICE')")
    public ResponseEntity<Object> deleteVariation() {
        try{

        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }
}
