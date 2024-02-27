package com.digitopia.digitopiacasestudy.service;

import com.digitopia.digitopiacasestudy.config.MD5;
import com.digitopia.digitopiacasestudy.dto.request.InvitationRequestDTO;
import com.digitopia.digitopiacasestudy.dto.response.InvitationResponseDTO;
import com.digitopia.digitopiacasestudy.enums.InvitationStatus;
import com.digitopia.digitopiacasestudy.exception.InvitationCodeNotFoundException;
import com.digitopia.digitopiacasestudy.mapper.InvitationServiceMapper;
import com.digitopia.digitopiacasestudy.model.Invitation;
import com.digitopia.digitopiacasestudy.model.User;
import com.digitopia.digitopiacasestudy.repository.InvitationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class InvitationService {

    private final UserService userService;

    private final InvitationRepository invitationRepository;

    public InvitationService(UserService userService, InvitationRepository invitationRepository) {
        this.userService = userService;
        this.invitationRepository = invitationRepository;
    }

    public InvitationResponseDTO create(InvitationRequestDTO invitationRequestDTO) {

        User user = userService.findByID(invitationRequestDTO.userID());

        boolean anyMatch = this.findAllInvitationsByUserWithStatus(user.getId()).stream().anyMatch(k -> k.invitationStatus().equals(InvitationStatus.PENDING));

        if (anyMatch)
            throw new IllegalArgumentException("User have more than one invitation.");

        Invitation invitation = new Invitation.Builder()
                .userID(invitationRequestDTO.userID())
                .invitationMessage(invitationRequestDTO.invitationMessage())
                .invitationCode(MD5.getMd5(user.getEmail()))
                .invitationStatus(InvitationStatus.PENDING)
                .userMail(user.getEmail())
                .invitationDate(LocalDateTime.now())
                .invitationExpiredDate(LocalDateTime.now().plusWeeks(1))
                .createdBy(invitationRequestDTO.userID())
                .updatedBy(invitationRequestDTO.userID())
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();

        if (invitationCreateControl(invitation)) {
            Invitation controlledInvitation = this.invitationRepository.save(invitation);

            return new InvitationResponseDTO(controlledInvitation.getInvitationID(),
                    controlledInvitation.getUserID(),
                    controlledInvitation.getInvitationMessage(),
                    controlledInvitation.getInvitationCode(),
                    controlledInvitation.getInvitationStatus(),
                    controlledInvitation.getUserMail());
        }

        throw new IllegalArgumentException("An unexpected error occurred, please try again later or contact the system administrator.");
    }

    public List<InvitationResponseDTO> getAll() {
        return InvitationServiceMapper.convertToDTO(invitationRepository.findAll());
    }

    public List<InvitationResponseDTO> findAllInvitationsByUserWithStatus(UUID id) {
        return InvitationServiceMapper.convertToDTO(invitationRepository.findByUserID(id));
    }

//    public Invitation

    public InvitationResponseDTO deleteByInvitationCode(String invitationCode) {
        InvitationResponseDTO invitation = findByInvitationCode(invitationCode);
        this.invitationRepository.deleteById(invitation.invitationID());
        return invitation;
    }

    public InvitationResponseDTO findByInvitationCode(String invitationCode) {
        if (invitationCode != null) {
            Invitation invitation = this.invitationRepository.findByInvitationCode(invitationCode)
                    .orElseThrow(
                            () -> new InvitationCodeNotFoundException(String.format("The invitation with the requested code %s could not be found.", invitationCode))
                    );
            return InvitationServiceMapper.convertToDTO(invitation);

        } else
            throw new IllegalArgumentException("An unexpected error occurred, please try again later or contact the system administrator.");
    }

    private boolean invitationCreateControl(Invitation invitation) {
        if (invitation != null && invitation.getInvitationCode() != null) {
            Invitation controlledInvitation = this.invitationRepository.findByInvitationCode(invitation.getInvitationCode())
                    .orElse(null);
            return controlledInvitation == null ? true : false;
        }
        throw new IllegalArgumentException("An unexpected error occurred, please try again later or contact the system administrator.");
    }

    public InvitationResponseDTO updateByInvitationCode(UUID id, String newInvitationCode) {
        Invitation invitation = invitationRepository.findById(id).get();
        invitation.setInvitationCode(newInvitationCode);
        invitationRepository.save(invitation);
        return InvitationServiceMapper.convertToDTO(invitationRepository.findById(id).get());
    }
}
