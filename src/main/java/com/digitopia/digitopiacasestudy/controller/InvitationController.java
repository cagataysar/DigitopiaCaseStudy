package com.digitopia.digitopiacasestudy.controller;

import com.digitopia.digitopiacasestudy.dto.request.InvitationRequestDTO;
import com.digitopia.digitopiacasestudy.dto.response.InvitationResponseDTO;
import com.digitopia.digitopiacasestudy.enums.InvitationStatus;
import com.digitopia.digitopiacasestudy.model.Invitation;
import com.digitopia.digitopiacasestudy.service.InvitationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping(path = "invitation")
public class InvitationController {

    private final InvitationService invitationService;


    public InvitationController(InvitationService invitationService) {
        this.invitationService = invitationService;
    }

    @PostMapping
    public ResponseEntity<?> saveInvatation(@RequestBody InvitationRequestDTO invitation) {
        InvitationResponseDTO invitationResponseDTO = this.invitationService.create(invitation);
        return ResponseEntity.status(HttpStatus.CREATED).body(invitationResponseDTO);
    }

    @PatchMapping
    public ResponseEntity<?> updateInvitationByCode(@PathVariable(name = "id")UUID id, String newInvitationCode) {
        return ResponseEntity.ok(invitationService.updateByInvitationCode(id, newInvitationCode));
    }


    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(this.invitationService.getAll());
    }



    @GetMapping(path = "status/{id}")
    public ResponseEntity<?> getAllInvitationsByUserWithStatus(@PathVariable(name = "id")  UUID id){
        return ResponseEntity.ok(invitationService.findAllInvitationsByUserWithStatus(id));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteInvitationByInvitationCode(@RequestParam(name = "invitationCode") String invitationCode) {
        invitationService.deleteByInvitationCode(invitationCode);
        return ResponseEntity.ok("The invitation with the requested code deleted.");
    }
}
