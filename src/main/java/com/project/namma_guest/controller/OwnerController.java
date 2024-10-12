package com.project.namma_guest.controller;

import com.project.namma_guest.DTO.Request.Id;
import com.project.namma_guest.service.PayingGuestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/owner")
public class OwnerController {
    private final PayingGuestService payingGuestService;
    public OwnerController(PayingGuestService payingGuestService) {
        this.payingGuestService = payingGuestService;
    }

    // Test weather the microservice is up or not
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Welcome to Namma Guest this is a test API!");
    }

    // Set up the Hostel while onboarding
    @PostMapping("/hostelCreation")
    public ResponseEntity<?> setUpHostel(@RequestBody Id ownerId) {
        log.info("Setting up hostel for owner with id: {}", ownerId.getId());
        try{
            Long payingGuestId = payingGuestService.setUpHostel(ownerId.getId());
            return ResponseEntity.ok(payingGuestId);
        } catch (Exception e) {
            if (e.getMessage().contains("User already has a hostel")) {
                log.info("User with id - {} already has a hostel assigned.",ownerId.getId());
                return ResponseEntity.status(HttpStatus.CONFLICT).body("User already has a hostel assigned.");
            } else if (e.getMessage().contains("Owner not found")) {
                log.info("Owner with id - {} not found.", ownerId.getId());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Owner not found.");
            } else if (e.getMessage().contains("Improper Data Present")) {
                log.info("Owner with id - {} had made a request with improper data",ownerId.getId());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Improper Data Present.");
            } else {
                log.error("An error occurred: {} when owner with id - {} requested for making hostel", e.getMessage(), ownerId.getId());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error.");
            }
        }
    }

    // Set up the Room while Creation
    @PostMapping("/hostelCreation")
    public ResponseEntity<?> setUpRoom(@RequestBody Id ownerId) {
        //TODO: This is same as the above function but may be bit complex
        return ResponseEntity.ok(":)");
    }

    // Update the Hostel details after creating
    @PutMapping("/hostelUpdate/{hostelId}")
    public ResponseEntity<String> hostelDataUpdate(@RequestBody String hostelData, @PathVariable String hostelId) {
        // Step 0 - Validate the Incoming data weather null or no make a function in helper folder & validation Class - Unprocessable Entity 422
        // Step 1 - Validate the request (input validation, data format etc.) - 400 Bad Request
        // Step 2 - Get the UserId & HostelId & Check weather the person who is trying to update is the owner only - 403 Forbidden
        // Step 3 - Update the hostel details (only update allowed fields) - 403 Forbidden
        // Step 4 - Return the details of the hostel if updated - 200 OK
        String str = "Hostel Data: " + hostelData;
        return ResponseEntity.ok(str);
    }

    // Update the Hostel details after creating
    @PutMapping("/RoomUpdate/{roomId}")
    public ResponseEntity<String> roomDataUpdate(@PathVariable String roomId, @RequestBody String roomData) {
        // Step 0 - Validate the Incoming data weather null or no make a function in helper folder & validation Class - Unprocessable Entity 422
        // Step 1 - Validate the request (input validation, data format etc.) - 400 Bad Request
        // Step 2 - Get the UserId & HostelId & Check weather the person who is trying to update is the owner only - 403 Forbidden
        // Step 3 - Update the hostel details (only update allowed fields) - 403 Forbidden
        // Step 4 - Return the details of the hostel if updated - 200 OK
        String str = "Hostel Data: " + roomData;
        return ResponseEntity.ok(str);
    }

    // Load the hostel details from the database & to make a user look
    @GetMapping("/hostelDetails/{id}")
    public ResponseEntity<String> hostelDetails(@PathVariable String id) {
        // TODO: Implement this method & return the details about a PG
        // Step 0 - Check if the Id is valid or not (eg null) - 400 Bad Request
        // Step 1 - Check the payingGuestId if not exist - 404 NOT FOUND
        // Step 2 - Check the user id is the owner to paying guest id - 403 Forbidden
        // Step 3 - Get the details of the PG weather visibility is public or not - 403 FORBIDDEN
        // Step 4 - Return the details
        String str = "Hostel Details: getEmail";
        return ResponseEntity.ok(str);
    }

    @GetMapping("/roomDetails/{roomId}")
    public ResponseEntity<?> roomDetails(@PathVariable String roomId) {
        // TODO: Implement this method & return the details about a room in PG
        // Step 0 - Check if the Id is valid or not (eg null) - 400 Bad Request
        // Step 1 - Check the payingGuestId if not exist - 404 NOT FOUND
        // Step 2 - Check the user id is the owner to paying guest id - 403 Forbidden
        // Step 3 - Get the details of the PG weather visibility is public or not then the room visibility weather public or not- 403 FORBIDDEN
        // Step 4 - Return the details
        String str = "Hostel Details: getEmail";
        return ResponseEntity.ok(str);
    }

    // Delete the hostel details from the database
    @DeleteMapping("/hostelDeletion")
    public ResponseEntity<String> hostelDeletion() {
        //TODO: To be Implemented later on kindly dont touch it
        String str = "Hostel Details: deleteEmail";
        return ResponseEntity.ok("TO BE IMPLEMENTED AT LAST");
    }

    // User Data this helps user to edit their profile data
    @GetMapping("/userData")
    public ResponseEntity<String> userData() {
        //TODO: To be Implemented later on kindly dont touch it
        String str = "User Data: get the Email from JWT Token";
        return ResponseEntity.ok("TO BE IMPLEMENTED AT LAST");
    }
}